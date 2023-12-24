package com.example.githubapp.ui.favorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubapp.domain.model.UserItemModel
import com.example.githubapp.domain.usecase.DeleteFavoriteUseCase
import com.example.githubapp.domain.usecase.GetFavoriteUsersUseCase
import com.example.githubapp.extension.setThreadingValue
import com.example.githubapp.extension.toLiveData
import com.example.githubapp.extension.toSingleEvent
import com.example.githubapp.util.Result
import com.example.githubapp.util.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteUsersViewModel @Inject constructor(
    private val getFavoriteUsersUseCase: GetFavoriteUsersUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase,
) : ViewModel() {

    private val _favoriteUsers = MutableLiveData<UIState<List<UserItemModel>>>()
    val favoriteUsers = _favoriteUsers.toLiveData()

    private val _removeFavoriteError = MutableLiveData<String>()
    val removeFavoriteError = _removeFavoriteError.toSingleEvent()

    init {
        fetchFavoriteUsers()
    }

    private fun fetchFavoriteUsers() {
        _favoriteUsers.setThreadingValue(UIState.Loading)
        viewModelScope.launch {
            when (val response = getFavoriteUsersUseCase.execute(Unit)) {
                is Result.Success -> {
                    _favoriteUsers.setThreadingValue(UIState.Success(response.data))
                }

                is Result.Failure -> {
                    _favoriteUsers.setThreadingValue(UIState.Error(response.error))
                }
            }
        }
    }


    fun remoteUserFromFavorite(user: UserItemModel) {
        viewModelScope.launch {
            when (val response =
                deleteFavoriteUseCase.execute(DeleteFavoriteUseCase.Request(userItem = user))) {
                is Result.Success -> {
                    fetchFavoriteUsers()
                }

                is Result.Failure -> {
                    _removeFavoriteError.setThreadingValue(response.error.message.orEmpty())
                }
            }
        }
    }

    fun refreshFavorites() {
        fetchFavoriteUsers()
    }

}