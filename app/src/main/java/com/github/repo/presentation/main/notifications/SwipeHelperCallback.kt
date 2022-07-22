package com.github.repo.presentation.main.notifications

import android.graphics.Canvas
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.github.repo.presentation.main.notifications.adapter.NotificationAdapter

class SwipeHelperCallback(
    val swipeItem: (Int) -> Unit
) : ItemTouchHelper.Callback() {
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int = makeMovementFlags(0, ItemTouchHelper.LEFT)

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        swipeItem(viewHolder.adapterPosition)
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