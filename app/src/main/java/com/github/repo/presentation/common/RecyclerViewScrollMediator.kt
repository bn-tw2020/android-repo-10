package com.github.repo.presentation.common

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewScrollMediator(
    private val recyclerView: RecyclerView,
    private val nextPage: (Int) -> Unit
) : RecyclerView.OnScrollListener() {
    private var page = 1
    private val perPage = 20

    init {
        recyclerView.addOnScrollListener(this)
    }

    override fun onScrolled(scrolledRv: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(scrolledRv, dx, dy)
        Log.d("Tester", "check")
        if (checkPagingEnd(recyclerView.adapter!!.itemCount)) {
            Log.d("Tester", "${perPage * page} : ${recyclerView.adapter!!.itemCount}")
            return
        }

        val lastVisibleItemPosition =
            (scrolledRv.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
        val itemTotalCount = scrolledRv.adapter!!.itemCount - 1
        Log.d("Tester", "start")
        // 스크롤이 끝에 도달했는지 확인
        if (!recyclerView.canScrollVertically(1) && lastVisibleItemPosition == itemTotalCount) {
            Log.d("Tester", "scorll")
            nextPage(++page)
        }
    }

    // 지금까지 불러온 리스트의 개수와 실제로 불러온 리스트 개수를 비교한다.
    // 만일 적다면, 더 이상 페이지를 불러올 필요가 없다.
    // 만일 크거나 같다면, 다음 페이지를 불러올 수 있다. (하지만, 클 순 없음을 보장함)
    // 따라서, 실제로 불러온 리스트의 개수와 호출된 리스트의 개수를 다른지를 판별함
    private fun checkPagingEnd(count: Int): Boolean {
        val totCount = (perPage * page)
        if (totCount < count)
            ++page
        return (totCount != count)
    }

    fun initialize() {
        page = 1
    }
}