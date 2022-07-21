package com.github.repo.presentation.search

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.DividerItemDecoration
import com.github.repo.R
import com.github.repo.databinding.FragmentSearchBinding
import com.github.repo.domain.model.GithubSearch
import com.github.repo.presentation.common.*
import com.github.repo.presentation.search.adapter.RepositoryAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var listener: Clickable
    private lateinit var rvScrollManager: RecyclerViewScrollMediator
    private val viewModel by sharedViewModel<SearchViewModel>()
    private val imm: InputMethodManager by lazy { activity?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager }
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
        binding.viewModel = viewModel
        initView()
        observeData()
    }

    private fun initView() {
        showKeyboard()
        navigationIconSetting()
        editTextSetting()
        adapterSetting()
    }

    private fun navigationIconSetting() {
        binding.tbSearch.onClickNavigationIcon {
            hideKeyboard()
            listener.onClickBackButton()
        }
    }

    private fun editTextSetting() {
        binding.ivClear.setOnClickListener { viewModel.clearKeyword() }
        binding.etSearch.setOnFocusChangeListener { _, focus ->
            if (focus) {
                binding.layoutEdit.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.btn_deactive_focus_14dp)
            } else {
                binding.layoutEdit.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.btn_deactive_14dp)
            }
        }
        binding.etSearch.setOnKeyListener { _, keyCode, _ ->
            keyCode == KEYCODE_ENTER
        }
    }

    private fun adapterSetting() {
        binding.rvRepository.adapter = adapter
        rvScrollManager = RecyclerViewScrollMediator(binding.rvRepository) { page ->
            viewModel.getNextPage(page)
        }
        val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.recylerview_divider)?.let {
            dividerItemDecoration.setDrawable(it)
        }
        binding.rvRepository.addItemDecoration(dividerItemDecoration)
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    private fun observeData() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            with(state) {
                onSuccess { handleSuccess(it) }
                onError { handleError() }
                onLoading { handleLoading() }
            }
        }

        viewModel.searchKeyword.asLiveData().observe(viewLifecycleOwner) { keyword ->
            if (binding.etSearch.isFocused) {
                if (keyword.isNotBlank()) focusEditText()
                else unFocusEditText()
            } else {
                unFocusEditText()
            }
        }
    }

    private fun handleSuccess(result: GithubSearch) {
        binding.rvRepository.isVisible = true
        binding.pbLoading.isVisible = false
        binding.layoutBlank.isVisible = false
        adapter.submitList(result.items)
    }

    private fun handleLoading() {
        binding.pbLoading.isVisible = true
        binding.layoutBlank.isVisible = false
    }

    private fun handleError() {
        binding.rvRepository.isVisible = false
        binding.pbLoading.isVisible = false
        binding.layoutBlank.isVisible = true
        rvScrollManager.initialize()
    }

    private fun hideKeyboard() {
        binding.etSearch.clearFocus()
        imm.hideSoftInputFromWindow(binding.etSearch.windowToken, 0)
    }

    private fun showKeyboard() {
        binding.etSearch.requestFocus()
        imm.showSoftInput(binding.etSearch, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun focusEditText() {
        binding.ivSearch.isGone = true
        binding.ivClear.isVisible = true
    }

    private fun unFocusEditText() {
        binding.ivSearch.isVisible = true
        binding.ivClear.isGone = true
    }
}