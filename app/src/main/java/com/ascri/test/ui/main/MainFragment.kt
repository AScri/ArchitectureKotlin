package com.ascri.test.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ascri.test.R
import com.ascri.test.data.models.User
import com.ascri.test.ui.adapters.ImageBaseAdapter2
import com.ascri.test.ui.base.BaseFragment
import com.ascri.test.ui.helpers.PagingListener
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {
    private lateinit var viewModel: MainViewModel
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: ImageBaseAdapter2
    private var pagingListener = ScrollListener()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        subscribeLiveData()
        viewModel.fetchNextUsers()
    }

    private fun init() {
        refreshLayout.setOnRefreshListener(this)
        adapter = ImageBaseAdapter2(requireContext())
        linearLayoutManager = LinearLayoutManager(context)
        rvImages.layoutManager = linearLayoutManager
        rvImages.adapter = adapter
        pagingListener.attach(rvImages)
    }

    private fun subscribeLiveData() {
        viewModel.let {
            it.isLoading().observe(
                viewLifecycleOwner,
                Observer { isLoading ->
                    refreshLayout.isRefreshing = isLoading
                }
            )
            it.getLiveData().observe(viewLifecycleOwner, Observer {
                adapter.setData(generatedData())
                pagingListener.setPaused(false)
            })
        }
    }

    private fun generatedData(): List<User> = listOf(
        User(
            "asd",
            "https://w.wallhaven.cc/full/r2/wallhaven-r21j9w.jpg",
            listOf(
                "https://w.wallhaven.cc/full/r2/wallhaven-r21j9w.jpg",
                "https://w.wallhaven.cc/full/dg/wallhaven-dgywjm.jpg",
                "https://w.wallhaven.cc/full/13/wallhaven-13yrvg.jpg",
                "https://w.wallhaven.cc/full/r2/wallhaven-r21j9w.jpg",
                "https://w.wallhaven.cc/full/dg/wallhaven-dgywjm.jpg",
                "https://w.wallhaven.cc/full/13/wallhaven-13yrvg.jpg",
                "https://w.wallhaven.cc/full/r2/wallhaven-r21j9w.jpg",
                "https://w.wallhaven.cc/full/dg/wallhaven-dgywjm.jpg",
                "https://w.wallhaven.cc/full/13/wallhaven-13yrvg.jpg"
            )
        ),
        User(
            "asd",
            "https://w.wallhaven.cc/full/r2/wallhaven-r21j9w.jpg",
            listOf(
                "https://w.wallhaven.cc/full/r2/wallhaven-r21j9w.jpg",
                "https://w.wallhaven.cc/full/dg/wallhaven-dgywjm.jpg",
                "https://w.wallhaven.cc/full/13/wallhaven-13yrvg.jpg",
                "https://w.wallhaven.cc/full/r2/wallhaven-r21j9w.jpg",
                "https://w.wallhaven.cc/full/dg/wallhaven-dgywjm.jpg",
                "https://w.wallhaven.cc/full/13/wallhaven-13yrvg.jpg",
                "https://w.wallhaven.cc/full/r2/wallhaven-r21j9w.jpg",
                "https://w.wallhaven.cc/full/dg/wallhaven-dgywjm.jpg",
                "https://w.wallhaven.cc/full/13/wallhaven-13yrvg.jpg"
            )
        ),
        User(
            "asd",
            "https://w.wallhaven.cc/full/r2/wallhaven-r21j9w.jpg",
            listOf(
                "https://w.wallhaven.cc/full/r2/wallhaven-r21j9w.jpg",
                "https://w.wallhaven.cc/full/dg/wallhaven-dgywjm.jpg",
                "https://w.wallhaven.cc/full/13/wallhaven-13yrvg.jpg",
                "https://w.wallhaven.cc/full/r2/wallhaven-r21j9w.jpg",
                "https://w.wallhaven.cc/full/dg/wallhaven-dgywjm.jpg",
                "https://w.wallhaven.cc/full/13/wallhaven-13yrvg.jpg",
                "https://w.wallhaven.cc/full/r2/wallhaven-r21j9w.jpg",
                "https://w.wallhaven.cc/full/dg/wallhaven-dgywjm.jpg",
                "https://w.wallhaven.cc/full/13/wallhaven-13yrvg.jpg"
            )
        ),
        User(
            "asd",
            "https://w.wallhaven.cc/full/r2/wallhaven-r21j9w.jpg",
            listOf(
                "https://w.wallhaven.cc/full/r2/wallhaven-r21j9w.jpg",
                "https://w.wallhaven.cc/full/dg/wallhaven-dgywjm.jpg",
                "https://w.wallhaven.cc/full/13/wallhaven-13yrvg.jpg",
                "https://w.wallhaven.cc/full/r2/wallhaven-r21j9w.jpg",
                "https://w.wallhaven.cc/full/dg/wallhaven-dgywjm.jpg",
                "https://w.wallhaven.cc/full/13/wallhaven-13yrvg.jpg",
                "https://w.wallhaven.cc/full/r2/wallhaven-r21j9w.jpg",
                "https://w.wallhaven.cc/full/dg/wallhaven-dgywjm.jpg",
                "https://w.wallhaven.cc/full/13/wallhaven-13yrvg.jpg"
            )
        ),
        User(
            "asd",
            "https://w.wallhaven.cc/full/r2/wallhaven-r21j9w.jpg",
            listOf(
                "https://w.wallhaven.cc/full/r2/wallhaven-r21j9w.jpg",
                "https://w.wallhaven.cc/full/dg/wallhaven-dgywjm.jpg",
                "https://w.wallhaven.cc/full/13/wallhaven-13yrvg.jpg",
                "https://w.wallhaven.cc/full/r2/wallhaven-r21j9w.jpg",
                "https://w.wallhaven.cc/full/dg/wallhaven-dgywjm.jpg",
                "https://w.wallhaven.cc/full/13/wallhaven-13yrvg.jpg",
                "https://w.wallhaven.cc/full/r2/wallhaven-r21j9w.jpg",
                "https://w.wallhaven.cc/full/dg/wallhaven-dgywjm.jpg",
                "https://w.wallhaven.cc/full/13/wallhaven-13yrvg.jpg"
            )
        )
    )

    override fun onRefresh() {
        adapter.clear()
        viewModel.refresh()
    }

    private inner class ScrollListener : PagingListener() {
        var page: Int = 10

        override fun lastItemVisible() {
            page += 10
            pagingListener.setPaused(true)
            viewModel.fetchNextUsers()
        }
    }

    companion object {
        fun newInstance() = MainFragment()
        const val TAG = "MAIN_FRAGMENT"
    }
}
