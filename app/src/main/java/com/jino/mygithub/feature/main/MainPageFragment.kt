package com.jino.mygithub.feature.main

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.jino.mygithub.R
import com.jino.mygithub.base.BaseFragment
import com.jino.mygithub.databinding.FragmentMainPageBinding
import com.jino.mygithub.extend.invisible
import com.jino.mygithub.extend.viewbinding.bindViews
import com.jino.mygithub.extend.visible
import com.jino.mygithub.feature.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainPageFragment : BaseFragment(R.layout.fragment_main_page) {

    private lateinit var mViewModel :MainPageViewModel
    private val mViewBinding by bindViews<FragmentMainPageBinding>()

    private lateinit var mAdapter :MainPageAdapter

    override fun initListener() {
    }

    override fun initView() {
        mViewModel = ViewModelProvider(this).get(MainPageViewModel::class.java)
        mAdapter = MainPageAdapter()
        mViewBinding?.let{
            it.mainPageRv.let {rv->
                rv.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
                rv.adapter = mAdapter
            }

            mAdapter.addLoadStateListener {states->
                when (states.refresh) {
                    is LoadState.NotLoading -> {
//                        progressBar?.visibility = View.INVISIBLE
                        it.mainPageRv.visible()
                        it.mainPageFreshlayout.isRefreshing = false

                    }
                    is LoadState.Loading -> {
                        it.mainPageFreshlayout.isRefreshing = true
//                        progressBar?.visibility = View.VISIBLE
                        it.mainPageRv.invisible()
                    }
                    is LoadState.Error -> {
//                        progressBar?.visibility = View.INVISIBLE
                        it.mainPageFreshlayout.isRefreshing = false
                    }
                }
            }

            it.mainPageFreshlayout.setOnRefreshListener {
                it.mainPageRv.swapAdapter(mAdapter,true)
                mAdapter.refresh()
            }
        }
    }

    override fun initData() {
        lifecycleScope.launch {
            mViewModel.fetchMainPageData().collect {
                mAdapter.submitData(it)
            }
        }
    }

}