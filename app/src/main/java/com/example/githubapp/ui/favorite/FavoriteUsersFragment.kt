package com.example.githubapp.ui.favorite

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.githubapp.NavGraphDirections
import com.example.githubapp.base.BaseFragment
import com.example.githubapp.databinding.FragmentFavoriteUsersBinding
import com.example.githubapp.domain.model.UserItemModel
import com.example.githubapp.extension.attach
import com.example.githubapp.extension.detach
import com.example.githubapp.extension.errorDialog
import com.example.githubapp.extension.linearDivider
import com.example.githubapp.ui.detail.UserDetailFragment
import com.example.githubapp.ui.adapter.UsersAdapter
import com.example.githubapp.util.UIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteUsersFragment :
    BaseFragment<FragmentFavoriteUsersBinding>(FragmentFavoriteUsersBinding::inflate) {

    private val viewModel: FavoriteUsersViewModel by viewModels()
    private val adapter by lazy { UsersAdapter(::onHandleAdapterEvents) }
    private val itemDecoration by lazy { linearDivider() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()

        viewModel.favoriteUsers.observe(viewLifecycleOwner, ::favoriteUsersObserver)
        viewModel.removeFavoriteError.observe(viewLifecycleOwner, ::removeFavoriteErrorObserver)
    }

    private fun initialize() {
        UserDetailFragment.setFragmentResultListener(this) {
            if (it.not()) {
                viewModel.refreshFavorites()
            }
        }
        binding.usersRecyclerView.attach(adapter, itemDecoration)
    }

    private fun favoriteUsersObserver(response: UIState<List<UserItemModel>>) {
        setLoading(response is UIState.Loading)
        when (response) {
            is UIState.Success -> {
                response.data?.let {
                    adapter.submitList(it)
                    binding.noData.isVisible = it.isEmpty()
                    binding.usersRecyclerView.isVisible = it.isNotEmpty()
                }
            }

            is UIState.Error -> {
                errorDialog {
                    setMessage(response.error.message)
                }
            }

            is UIState.Loading -> {}
        }
    }

    private fun removeFavoriteErrorObserver(errorMessage: String) {
        errorDialog {
            setMessage(errorMessage)
        }
    }

    private fun onHandleAdapterEvents(event: UsersAdapter.Event) {
        when (event) {
            is UsersAdapter.Event.AddFavorite -> {
                //unnecessary for this page
            }

            is UsersAdapter.Event.RemoveFavorite -> {
                viewModel.remoteUserFromFavorite(event.user)
            }

            is UsersAdapter.Event.ItemClick -> {
                findNavController().navigate(NavGraphDirections.actionUserDetailFragment(username = event.username))
            }
        }
    }

    override fun onDestroyView() {
        binding.usersRecyclerView.detach()
        super.onDestroyView()
    }

}