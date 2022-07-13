package com.github.repo.presentation.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.repo.databinding.FragmentSearchBinding
import com.github.repo.presentation.common.Clickable

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var listener: Clickable

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

        initView()
    }

    private fun initView() {
        binding.tbSearch.onClickNavigationIcon { listener.onClickBackButton() }
    }
}