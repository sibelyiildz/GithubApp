package com.example.githubapp.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.githubapp.R
import com.example.githubapp.base.BaseFragment
import com.example.githubapp.databinding.FragmentUserDetailBinding
import com.example.githubapp.domain.model.UserDetailModel
import com.example.githubapp.extension.errorDialog
import com.example.githubapp.extension.getDrawable
import com.example.githubapp.extension.setImageUrl
import com.example.githubapp.util.UIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailFragment :
    BaseFragment<FragmentUserDetailBinding>(FragmentUserDetailBinding::inflate) {

    companion object {
        private const val FAVORITE_STATE = "favorite_state_changed"
        fun setFragmentResultListener(
            fragment: Fragment, block: (isFavorite: Boolean) -> Unit
        ) {
            fragment.setFragmentResultListener(FAVORITE_STATE) { _, bundle ->
                block.invoke(bundle.getBoolean(FAVORITE_STATE))
            }
        }
    }

    private val viewModel: UserDetailViewModel by viewModels()
    private val args by navArgs<UserDetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchUserDetail(args.username)
        viewModel.userDetail.observe(viewLifecycleOwner, ::userDetailObserver)
        viewModel.userFavoriteTransactions.observe(
            viewLifecycleOwner,
            ::userFavoriteTransactionsObserver
        )
    }

    private fun userDetailObserver(response: UIState<UserDetailModel>) {
        setLoading(response is UIState.Loading)
        when (response) {
            is UIState.Success -> {
                response.data?.let {
                    setupUI(it)
                }
                Log.v("LogTag", "Success -> ${response.data}")
            }

            is UIState.Error -> {
                errorDialog {
                    setMessage(response.error.message)
                }
                Log.v("LogTag", "Error")
            }

            is UIState.Loading -> {
                Log.v("LogTag", "Loading")
            }
        }
    }

    private fun userFavoriteTransactionsObserver(response: UIState<Boolean>) {
        setLoading(response is UIState.Loading)
        when (response) {
            is UIState.Success -> {
                response.data?.let { isFavorite ->
                    binding.favoriteIcon.setImageDrawable(
                        if (isFavorite) getDrawable(R.drawable.ic_star_filled) else getDrawable(
                            R.drawable.ic_star
                        )
                    )
                    setFragmentResult(FAVORITE_STATE, bundleOf(FAVORITE_STATE to isFavorite))
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


    private fun setupUI(response: UserDetailModel) {
        with(binding) {
            userImage.setImageUrl(requireContext(), response.avatarUrl)
            userName.text = response.name
            userNickname.text = response.login
            favoriteIcon.setImageDrawable(
                if (response.isFavorite) getDrawable(R.drawable.ic_star_filled) else getDrawable(
                    R.drawable.ic_star
                )
            )
            favoriteIcon.setOnClickListener {
                if (response.isFavorite) {
                    viewModel.remoteUserFromFavorite(response)
                } else {
                    viewModel.addUserToFavorite(response)
                }
            }
        }
    }


}