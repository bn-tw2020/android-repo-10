package com.github.repo.presentation.main.notifications

import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.github.repo.databinding.FragmentNotificationsBinding
import com.github.repo.domain.model.Notification
import com.github.repo.presentation.common.onError
import com.github.repo.presentation.common.onLoading
import com.github.repo.presentation.common.onSuccess
import com.github.repo.presentation.main.notifications.adapter.NotificationAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotificationsFragment : Fragment() {

    lateinit var binding: FragmentNotificationsBinding
    lateinit var rvAdapter: NotificationAdapter
    private val viewModel: NotificationsViewModel by viewModel<NotificationsViewModel>()

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

        viewModel.getNotifications()
        initView()
    }

    private fun initView() {
        recyclerViewAdapterSetting()
        observeData()
    }

    private fun recyclerViewAdapterSetting() {
        rvAdapter = NotificationAdapter()
        binding.rvNotification.adapter = rvAdapter

        val simpleItemTouchCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = true

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val removeItem = rvAdapter.removeItem(viewHolder.adapterPosition)
                viewModel.removeNotification(removeItem.threadId)
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    getDefaultUIUtil().onDraw(
                        c,
                        recyclerView,
                        (viewHolder as NotificationAdapter.ViewHolder).binding.layoutSwipe,
                        dX,
                        dY,
                        actionState,
                        isCurrentlyActive
                    )
                }
            }

            override fun clearView(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
            ) =
                getDefaultUIUtil().clearView((viewHolder as NotificationAdapter.ViewHolder).binding.layoutSwipe)
        }
        ItemTouchHelper(simpleItemTouchCallback)
            .attachToRecyclerView(binding.rvNotification)
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
        binding.rvNotification.isGone = true
        binding.tvError.isGone = true
    }

    private fun handleSuccess(list: List<Notification>) {
        rvAdapter.addItemList(list)
        binding.rvNotification.isVisible = true
        binding.pbLoading.isGone = true
        binding.tvError.isGone = true
    }
}