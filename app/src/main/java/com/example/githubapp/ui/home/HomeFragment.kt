package com.example.githubapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import com.example.githubapp.base.BaseFragment
import com.example.githubapp.data.remote.model.UserItemResponse
import com.example.githubapp.databinding.FragmentHomeBinding
import com.example.githubapp.extension.attach
import com.example.githubapp.extension.detach
import com.example.githubapp.extension.errorDialog
import com.example.githubapp.extension.linearDivider
import com.example.githubapp.util.UIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()
    private val adapter by lazy { UsersAdapter() }
    private val itemDecoration by lazy { linearDivider() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.usersRecyclerView.attach(adapter, itemDecoration)
        binding.searchView.setOnQueryTextListener(searchQueryTextListener)

        viewModel.users.observe(viewLifecycleOwner, ::usersObserver)
    }

    private fun usersObserver(response: UIState<List<UserItemResponse>>) {
        setLoading(response is UIState.Loading)
        when (response) {
            is UIState.Success -> {
                Log.v("LogTag", "response -> ${response.data}")
                adapter.submitList(response.data)
            }

            is UIState.Error -> {
                errorDialog {
                    setMessage(response.error.message)
                }
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

    override fun onDestroyView() {
        binding.usersRecyclerView.detach()
        super.onDestroyView()
    }
}