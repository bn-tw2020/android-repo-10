package com.github.repo.presentation.main.notifications

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import com.github.repo.databinding.FragmentNotificationsBinding
import com.github.repo.domain.model.Notification
import com.github.repo.presentation.common.RecyclerViewScrollMediator
import com.github.repo.presentation.common.onError
import com.github.repo.presentation.common.onLoading
import com.github.repo.presentation.common.onSuccess
import com.github.repo.presentation.main.notifications.adapter.NotificationAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotificationsFragment : Fragment() {

    lateinit var binding: FragmentNotificationsBinding
    lateinit var rvAdapter: NotificationAdapter
    private val viewModel: NotificationsViewModel by viewModel<NotificationsViewModel>()
    private lateinit var recyclerViewScrollMediator: RecyclerViewScrollMediator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.getNotifications(1)
        initView()
    }

    override fun onStop() {
        Log.d("Tester", "onStop: hi")
        super.onStop()
        viewModel.removeNotification()
    }

    private fun initView() {
        recyclerViewAdapterSetting()
        observeData()
    }

    private fun recyclerViewAdapterSetting() {
        rvAdapter = NotificationAdapter()
        binding.rvNotification.adapter = rvAdapter
        recyclerViewScrollMediator = RecyclerViewScrollMediator(binding.rvNotification) { page ->
            viewModel.getNotifications(page)
        }

        val swipeHelper = SwipeHelperCallback { position ->
            val removeItem = rvAdapter.removeItem(position)
            viewModel.addToRemoveCache(removeItem.threadId)
        }
        ItemTouchHelper(swipeHelper).attachToRecyclerView(binding.rvNotification)
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
        binding.rvNotification.isGone = true
        binding.pbLoading.isGone = true
    }

    private fun handleLoading() {
        binding.pbLoading.isVisible = true
        binding.tvError.isGone = true
    }

    private fun handleSuccess(list: List<Notification>) {
        rvAdapter.addItemList(list)
        binding.rvNotification.isVisible = true
        binding.pbLoading.isGone = true
        binding.tvError.isGone = true
    }
}