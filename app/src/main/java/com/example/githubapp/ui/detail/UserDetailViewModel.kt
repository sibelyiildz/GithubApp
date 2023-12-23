package com.example.githubapp.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubapp.domain.model.UserDetailModel
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
) : ViewModel() {

    private val _userDetail = MutableLiveData<UIState<UserDetailModel>>()
    val userDetail = _userDetail.toLiveData()

    fun addUserToFavorite(username: String) {
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

}