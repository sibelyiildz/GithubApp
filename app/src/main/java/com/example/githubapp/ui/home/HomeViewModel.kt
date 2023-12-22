package com.example.githubapp.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubapp.domain.usecase.SearchUsersUseCase
import com.example.githubapp.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val searchUsersUseCase: SearchUsersUseCase
) : ViewModel() {

    fun searchUsers(keyword: String) {
        viewModelScope.launch {
            when (val response =
                searchUsersUseCase.execute(SearchUsersUseCase.Request(keyword = keyword))) {
                is Result.Success -> {
                    Log.v("LogTag", "response -> ${response.data}")
                }

                is Result.Failure -> {
                    Log.v("LogTag", "Failure -> ${response.error}")
                }
            }
        }

    }
}