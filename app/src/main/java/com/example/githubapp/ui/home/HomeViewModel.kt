package com.example.githubapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubapp.domain.model.UserItemModel
import com.example.githubapp.domain.usecase.AddFavoriteUseCase
import com.example.githubapp.domain.usecase.SearchUsersUseCase
import com.example.githubapp.extension.setThreadingValue
import com.example.githubapp.extension.toLiveData
import com.example.githubapp.util.Result
import com.example.githubapp.util.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val searchUsersUseCase: SearchUsersUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
) : ViewModel() {

    private val _users = MutableLiveData<UIState<List<UserItemModel>>>()
    val users = _users.toLiveData()

    private val _userFavoriteTransactions = MutableLiveData<UIState<Pair<UserItemModel, Boolean>>>()
    val userFavoriteTransactions = _userFavoriteTransactions.toLiveData()

    fun searchUsers(keyword: String) {
        _users.setThreadingValue(UIState.Loading)
        if (keyword.isNotEmpty()) {
            viewModelScope.launch {
                when (val response =
                    searchUsersUseCase.execute(SearchUsersUseCase.Request(keyword = keyword))) {
                    is Result.Success -> {
                        _users.setThreadingValue(UIState.Success(response.data))
                    }

                    is Result.Failure -> {
                        _users.setThreadingValue(UIState.Error(response.error))
                    }
                }
            }
        } else {
            _users.setThreadingValue(UIState.Success(arrayListOf()))
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

    }

}