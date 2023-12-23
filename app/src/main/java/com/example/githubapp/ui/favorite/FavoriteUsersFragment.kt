package com.example.githubapp.ui.favorite

import android.os.Bundle
import android.view.View
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
import com.example.githubapp.ui.home.UsersAdapter
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
        viewModel.userFavoriteTransactions.observe(
            viewLifecycleOwner,
            ::userFavoriteTransactionsObserver
        )
    }

    private fun initialize() {
        binding.usersRecyclerView.attach(adapter, itemDecoration)
    }

    private fun favoriteUsersObserver(response: UIState<List<UserItemModel>>) {
        setLoading(response is UIState.Loading)
        when (response) {
            is UIState.Success -> {
                adapter.submitList(response.data)
            }

            is UIState.Error -> {
                errorDialog {
                    setMessage(response.error.message)
                }
            }

            is UIState.Loading -> {}
        }
    }

    private fun userFavoriteTransactionsObserver(response: UIState<Pair<UserItemModel, Boolean>>) {
        setLoading(response is UIState.Loading)
        when (response) {
            is UIState.Success -> {
                response.data?.let {
                    adapter.updateItem(it.first, it.second)
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

    private fun onHandleAdapterEvents(event: UsersAdapter.Event) {
        when (event) {
            is UsersAdapter.Event.AddFavorite -> {
                viewModel.addUserToFavorite(event.user)
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