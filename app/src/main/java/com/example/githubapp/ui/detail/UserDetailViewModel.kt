package com.example.githubapp.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubapp.domain.model.UserDetailModel
import com.example.githubapp.domain.model.UserItemModel
import com.example.githubapp.domain.usecase.AddFavoriteUseCase
import com.example.githubapp.domain.usecase.DeleteFavoriteUseCase
import com.example.githubapp.domain.usecase.GetUserDetailUseCase
import com.example.githubapp.extension.setThreadingValue
import com.example.githubapp.extension.toLiveData
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

    private val _userFavoriteTransactions = MutableLiveData<UIState<Pair<UserItemModel, Boolean>>>()
    val userFavoriteTransactions = _userFavoriteTransactions.toLiveData()


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

    fun addUserToFavorite(user: UserItemModel) {
        _userFavoriteTransactions.setThreadingValue(UIState.Loading)
        viewModelScope.launch {
            when (val response =
                addFavoriteUseCase.execute(AddFavoriteUseCase.Request(userItem = user))) {
                is Result.Success -> {
                    _userFavoriteTransactions.setThreadingValue(UIState.Success(Pair(user, true)))
                }

                is Result.Failure -> {
                    _userFavoriteTransactions.setThreadingValue(UIState.Error(response.error))
                }
            }
        }
    }

    fun remoteUserFromFavorite(user: UserItemModel) {
        _userFavoriteTransactions.setThreadingValue(UIState.Loading)
        viewModelScope.launch {
            when (val response =
                deleteFavoriteUseCase.execute(DeleteFavoriteUseCase.Request(userItem = user))) {
                is Result.Success -> {
                    _userFavoriteTransactions.setThreadingValue(UIState.Success(Pair(user, false)))
                }

                is Result.Failure -> {
                    _userFavoriteTransactions.setThreadingValue(UIState.Error(response.error))
                }
            }
        }
    }

}