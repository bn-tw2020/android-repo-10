package com.github.repo.presentation.main.issue

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import com.github.repo.R
import com.github.repo.databinding.FragmentIssueBinding
import com.github.repo.domain.model.GithubIssue
import com.github.repo.presentation.common.onError
import com.github.repo.presentation.common.onLoading
import com.github.repo.presentation.common.onSuccess
import com.github.repo.presentation.main.issue.OptionType.*
import com.github.repo.presentation.main.issue.adapter.IssueAdapter
import com.github.repo.presentation.main.issue.adapter.SpinnerAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class IssueFragment : Fragment() {

    private lateinit var binding: FragmentIssueBinding
    private val viewModel: IssueViewModel by viewModel()
    private lateinit var spinnerAdapter: SpinnerAdapter
    private val issueAdapter = IssueAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIssueBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = viewLifecycleOwner
        initView()
        observeData()
    }

    private fun initView() {
        adapterSetting()
    }

    private fun adapterSetting() {
        binding.rvIssue.adapter = issueAdapter
        val dividerItemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.recylerview_divider)?.let {
            dividerItemDecoration.setDrawable(it)
        }
        binding.rvIssue.addItemDecoration(dividerItemDecoration)
    }

    private fun observeData() {
        viewModel.items.observe(viewLifecycleOwner) { items ->
            spinnerAdapter = SpinnerAdapter(requireContext(), items)
            binding.spinnerIssueFilter.adapter = spinnerAdapter
            setSpinnerSetting()
        }

        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            with(state) {
                onSuccess { handleSuccess(it) }
                onError { handleError() }
                onLoading { handleLoading() }
            }
        }
    }

    private fun handleLoading() {
        binding.pbLoading.isVisible = true
        binding.rvIssue.isGone = true
        binding.tvError.isGone = true
    }

    private fun handleError() {
        binding.tvError.isVisible = true
        binding.rvIssue.isGone = true
        binding.pbLoading.isGone = true
    }

    private fun handleSuccess(it: List<GithubIssue>) {
        issueAdapter.submitList(it)
        binding.rvIssue.isVisible = true
        binding.pbLoading.isGone = true
        binding.tvError.isGone = true
    }

    private fun setSpinnerSetting() {
        binding.layoutFilter.setOnClickListener { binding.spinnerIssueFilter.performClick() }
        binding.spinnerIssueFilter.viewTreeObserver.addOnWindowFocusChangeListener {
            when (it) {
                true -> binding.layoutFilter.setBackgroundResource(R.drawable.btn_deactive_14dp)
                else -> binding.layoutFilter.setBackgroundResource(R.drawable.btn_deactive_focus_14dp)
            }
        }

        binding.spinnerIssueFilter.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?, view: View?, position: Int, id: Long
                ) {
                    spinnerAdapter.setSelectedPosition(position)
                    when (position) {
                        OPEN.position -> viewModel.getIssues(OPEN.optionName)
                        CLOSED.position -> viewModel.getIssues(CLOSED.optionName)
                        ALL.position -> viewModel.getIssues(ALL.optionName)
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) = Unit
            }
    }
}