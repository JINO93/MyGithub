package com.jino.mygithub.feature.search

import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.jino.mygithub.R
import com.jino.mygithub.base.BaseFragment
import com.jino.mygithub.databinding.FragmentSearchBinding
import com.jino.mygithub.extend.invisible
import com.jino.mygithub.extend.viewbinding.bindViews
import com.jino.mygithub.extend.visible
import com.jino.mygithub.feature.main.MainPageFragment
import com.jino.mygithub.util.LogUtils
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SearchFragment : BaseFragment(R.layout.fragment_search) {

    private lateinit var mViewModel: SearchViewModel
    private val mViewBinding by bindViews<FragmentSearchBinding>()

    private lateinit var mAdapter: SearchPageAdapter

    override fun initListener() {

    }

    override fun initView() {
        mViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        mAdapter = SearchPageAdapter()
        mViewBinding?.let {
            it.searchIconBtn.setOnClickListener { v->
                val text = it.searchEditText.text.toString()
                if(text.isEmpty()){
                    Toast.makeText(context, "请输入搜索内容", Toast.LENGTH_SHORT).show()
                }else{
                    viewLifecycleOwner.lifecycleScope.launch {
                        mViewModel.searchWithKeyword(text).collect {
                            LogUtils.d(MainPageFragment.TAG,"onData:$it")
                            mAdapter.submitData(it)
                        }
                    }
                }
            }

            with(it.rvSearch){
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
                adapter = mAdapter
            }

            mAdapter.addLoadStateListener {states->
                LogUtils.d("TAG","list addLoadStateListener states:$states")
                when (states.refresh) {
                    is LoadState.NotLoading -> {
//                        progressBar?.visibility = View.INVISIBLE
                        it.rvSearch.visible()

                    }
                    is LoadState.Loading -> {
//                        progressBar?.visibility = View.VISIBLE
                        it.rvSearch.invisible()
                    }
                    is LoadState.Error -> {
//                        progressBar?.visibility = View.INVISIBLE
                    }
                }
            }
        }
    }

    override fun initData() {

    }

}