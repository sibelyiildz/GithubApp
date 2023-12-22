package com.example.githubapp.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.githubapp.base.BaseFragment
import com.example.githubapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}