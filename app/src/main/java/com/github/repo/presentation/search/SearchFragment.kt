package com.github.repo.presentation.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.github.repo.R
import com.github.repo.databinding.FragmentSearchBinding
import com.github.repo.presentation.common.Clickable
import com.github.repo.presentation.search.adapter.RepositoryAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var listener: Clickable
    private val viewModel by viewModel<SearchViewModel>()
    private val adapter = RepositoryAdapter()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Clickable) {
            context.also { listener = it }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner

        initView()
        observeData()
    }

    private fun initView() {
        binding.tbSearch.onClickNavigationIcon { listener.onClickBackButton() }
        adapterSetting()
        binding.etSearch.doAfterTextChanged { text ->
            val keyword = text.toString()
            if (keyword.isBlank()) {
                handleError()
            } else {
                viewModel.searchRepositories(keyword)
            }
        }
    }

    private fun adapterSetting() {
        binding.rvRepository.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.recylerview_divider)?.let {
            dividerItemDecoration.setDrawable(it)
        }
        binding.rvRepository.addItemDecoration(dividerItemDecoration)
    }

    private fun observeData() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UiState.Error -> handleError()
                is UiState.Loading -> handleLoading()
                is UiState.GetRepositories -> handleSuccess(state)
            }
        }
    }

    private fun handleSuccess(state: UiState.GetRepositories) {
        binding.rvRepository.isVisible = true
        binding.pbLoading.isVisible = false
        binding.layoutBlank.isVisible = false
        adapter.submitList(state.repositories.items)
    }

    private fun handleLoading() {
        binding.rvRepository.isVisible = false
        binding.pbLoading.isVisible = true
        binding.layoutBlank.isVisible = false
    }

    private fun handleError() {
        binding.rvRepository.isVisible = false
        binding.pbLoading.isVisible = false
        binding.layoutBlank.isVisible = true
    }
}