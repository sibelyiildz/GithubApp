package com.example.githubapp.ui.detail

import androidx.fragment.app.viewModels
import com.example.githubapp.base.BaseFragment
import com.example.githubapp.databinding.FragmentUserDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailFragment :
    BaseFragment<FragmentUserDetailBinding>(FragmentUserDetailBinding::inflate) {

    private val viewModel: UserDetailViewModel by viewModels()


}