package com.github.repo.presentation.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.github.repo.databinding.FragmentProfileBinding
import com.github.repo.domain.model.Profile
import com.github.repo.presentation.common.Clickable
import com.github.repo.presentation.common.onError
import com.github.repo.presentation.common.onLoading
import com.github.repo.presentation.common.onSuccess
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ProfileFragment : Fragment() {

    lateinit var binding: FragmentProfileBinding
    private val viewModel by sharedViewModel<ProfileViewModel>()
    private lateinit var listener: Clickable

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        initView()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Clickable) {
            context.also { listener = it }
        }
    }


    private fun initView() {
        observeData()
        buttonSetting()
    }

    private fun buttonSetting() {
        binding.tbProfile.onClickNavigationIcon {
            listener.onClickBackButton()
        }
    }

    private fun observeData() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            with(state) {
                onSuccess { handleSuccess(it) }
                onError { handleError() }
                onLoading { handleLoading() }
            }
        }
    }

    private fun handleError() {
        binding.tvError.isVisible = true
        binding.layoutProfile.isGone = true
        binding.pbLoading.isGone = true
    }

    private fun handleLoading() {
        binding.pbLoading.isVisible = true
        binding.layoutProfile.isGone = true
        binding.tvError.isGone = true
    }

    private fun handleSuccess(profile: Profile) {
        binding.layoutProfile.isVisible = true
        binding.pbLoading.isGone = true
        binding.tvError.isGone = true

        binding.profile = profile
    }
}