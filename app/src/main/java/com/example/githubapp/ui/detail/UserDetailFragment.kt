package com.example.githubapp.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.githubapp.base.BaseFragment
import com.example.githubapp.databinding.FragmentUserDetailBinding
import com.example.githubapp.domain.model.UserDetailModel
import com.example.githubapp.extension.errorDialog
import com.example.githubapp.util.UIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailFragment :
    BaseFragment<FragmentUserDetailBinding>(FragmentUserDetailBinding::inflate) {

    private val viewModel: UserDetailViewModel by viewModels()
    private val args by navArgs<UserDetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchUserDetail(args.username)
        viewModel.userDetail.observe(viewLifecycleOwner, ::userDetailObserver)
    }

    private fun userDetailObserver(response: UIState<UserDetailModel>) {
        setLoading(response is UIState.Loading)
        when (response) {
            is UIState.Success -> {
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


}