package com.ascri.test.ui.helpers

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PagingListener : RecyclerView.OnScrollListener() {

    private var adapter: RecyclerView.Adapter<*>? = null
    private var enabled = true
    private var size: Int = 0
    private var paused: Boolean = false


    fun attach(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(this)
        adapter = recyclerView.adapter
    }

    fun detach(recyclerView: RecyclerView) {
        recyclerView.removeOnScrollListener(this)
        adapter = null
    }

    fun setEnabled(enabled: Boolean) {
        this.enabled = enabled
    }

    fun isPaused(): Boolean {
        return paused
    }

    fun setPaused(paused: Boolean) {
        this.paused = paused
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        if (enabled and !paused) {
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager?
            size = recyclerView.adapter!!.itemCount
            val last = layoutManager!!.findLastVisibleItemPosition()
            if (last == size - 1) {
                lastItemVisible()
            }
        }
    }

    protected abstract fun lastItemVisible()
}