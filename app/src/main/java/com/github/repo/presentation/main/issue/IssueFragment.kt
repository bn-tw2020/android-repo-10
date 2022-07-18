package com.github.repo.presentation.main.issue

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import com.github.repo.R
import com.github.repo.databinding.FragmentIssueBinding
import com.github.repo.presentation.main.issue.adapter.SpinnerAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class IssueFragment : Fragment() {

    private lateinit var binding: FragmentIssueBinding
    private val viewModel: IssueViewModel by viewModel()
    private lateinit var spinnerAdapter: SpinnerAdapter

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
        observeData()
    }

    private fun observeData() {
        viewModel.items.observe(viewLifecycleOwner) { items ->
            spinnerAdapter = SpinnerAdapter(requireContext(), items)
            binding.spinnerIssueFilter.adapter = spinnerAdapter
            setSpinnerSetting()
        }
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
                ) = spinnerAdapter.setSelectedPosition(position)

                override fun onNothingSelected(parent: AdapterView<*>?) = Unit
            }
    }
}