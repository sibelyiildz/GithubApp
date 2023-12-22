package com.example.githubapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubapp.data.remote.model.SearchItemResponse
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
    private val searchUsersUseCase: SearchUsersUseCase
) : ViewModel() {

    private val _users = MutableLiveData<UIState<List<SearchItemResponse>>>()
    val users = _users.toLiveData()

    fun searchUsers(keyword: String) {
        _users.setThreadingValue(UIState.Loading)
        if (keyword.isNotEmpty()) {
            viewModelScope.launch {
                when (val response =
                    searchUsersUseCase.execute(SearchUsersUseCase.Request(keyword = keyword))) {
                    is Result.Success -> {
                        _users.setThreadingValue(UIState.Success(response.data.items))
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

}