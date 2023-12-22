package com.example.githubapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import com.example.githubapp.base.BaseFragment
import com.example.githubapp.data.remote.model.SearchResponse
import com.example.githubapp.databinding.FragmentHomeBinding
import com.example.githubapp.util.UIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchView.setOnQueryTextListener(searchQueryTextListener)

        viewModel.users.observe(viewLifecycleOwner, ::usersObserver)
    }

    private fun usersObserver(response: UIState<SearchResponse>) {
        when (response) {
            is UIState.Success -> {
                Log.v("LogTag", "response -> ${response.data}")
            }

            is UIState.Error -> {
                Log.v("LogTag", "Failure -> ${response.error}")
            }

            is UIState.Loading -> {
                Log.v("LogTag", "Loading ")
            }
        }
    }

    private val searchQueryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            viewModel.searchUsers(newText.orEmpty())
            return true
        }
    }
}