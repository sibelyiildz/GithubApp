package com.example.githubapp.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubapp.domain.mapper.toUserItemModel
import com.example.githubapp.domain.model.UserDetailModel
import com.example.githubapp.domain.usecase.AddFavoriteUseCase
import com.example.githubapp.domain.usecase.DeleteFavoriteUseCase
import com.example.githubapp.domain.usecase.GetUserDetailUseCase
import com.example.githubapp.extension.setThreadingValue
import com.example.githubapp.extension.toLiveData
import com.example.githubapp.extension.toSingleEvent
import com.example.githubapp.util.Result
import com.example.githubapp.util.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val getUserDetailUseCase: GetUserDetailUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
) : ViewModel() {

    private val _userDetail = MutableLiveData<UIState<UserDetailModel>>()
    val userDetail = _userDetail.toLiveData()

    private val _userFavoriteTransactions = MutableLiveData<UIState<Boolean>>()
    val userFavoriteTransactions = _userFavoriteTransactions.toSingleEvent()


    fun fetchUserDetail(username: String) {
        _userDetail.setThreadingValue(UIState.Loading)
        viewModelScope.launch {
            when (val response =
                getUserDetailUseCase.execute(GetUserDetailUseCase.Request(username = username))) {
                is Result.Success -> {
                    _userDetail.setThreadingValue(UIState.Success(response.data))
                }

                is Result.Failure -> {
                    _userDetail.setThreadingValue(UIState.Error(response.error))
                }
            }
        }
    }

    fun addUserToFavorite(user: UserDetailModel) {
        _userFavoriteTransactions.setThreadingValue(UIState.Loading)
        viewModelScope.launch {
            when (val response =
                addFavoriteUseCase.execute(AddFavoriteUseCase.Request(userItem = user.toUserItemModel()))) {
                is Result.Success -> {
                    _userFavoriteTransactions.setThreadingValue(UIState.Success(true))
                }

                is Result.Failure -> {
                    _userFavoriteTransactions.setThreadingValue(UIState.Error(response.error))
                }
            }
        }
    }

    fun remoteUserFromFavorite(user: UserDetailModel) {
        _userFavoriteTransactions.setThreadingValue(UIState.Loading)
        viewModelScope.launch {
            when (val response =
                deleteFavoriteUseCase.execute(DeleteFavoriteUseCase.Request(userItem = user.toUserItemModel()))) {
                is Result.Success -> {
                    _userFavoriteTransactions.setThreadingValue(UIState.Success(false))
                }

                is Result.Failure -> {
                    _userFavoriteTransactions.setThreadingValue(UIState.Error(response.error))
                }
            }
        }
    }

}