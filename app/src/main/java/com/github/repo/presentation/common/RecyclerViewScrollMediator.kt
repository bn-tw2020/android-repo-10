package com.github.repo.presentation.common

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewScrollMediator(
    private val recyclerView: RecyclerView,
    private val nextPage: (Int) -> Unit
) : RecyclerView.OnScrollListener() {
    private var page = 1

    init {
        recyclerView.addOnScrollListener(this)
    }

    override fun onScrolled(scrolledRv: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(scrolledRv, dx, dy)
        if (checkPagingEnd(recyclerView.adapter!!.itemCount)) return

        val lastVisibleItemPosition =
            (scrolledRv.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
        val itemTotalCount = scrolledRv.adapter!!.itemCount - 1
        if (!recyclerView.canScrollVertically(1) && lastVisibleItemPosition == itemTotalCount) {
            nextPage(++page)
        }
    }

    /**
     * @description 리스트의 개수와 실제 데이터 개수 비교 (실제로 불러온 데이터의 개수와 호출된 리스트의 개수를 판별)
     * 적은 경우 : 추가 데이터 호출 불필요
     * 크거나 같은 경우 : 추가 데이터 호출 가능
     */
    private fun checkPagingEnd(count: Int): Boolean {
        val totCount = (perPage * page)
        if (totCount < count)
            ++page
        return (totCount != count)
    }

    fun initialize() {
        page = 1
    }

    companion object {
        const val perPage = 20
    }
}