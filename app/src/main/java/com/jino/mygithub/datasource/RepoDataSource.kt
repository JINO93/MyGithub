package com.jino.mygithub.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jino.mygithub.Repository.RepoRepository
import com.jino.mygithub.base.LoadStatus
import com.jino.mygithub.bean.ui.ReposUIModel
import com.jino.mygithub.util.LogUtils
import kotlinx.coroutines.flow.collect

class RepoDataSource(private val repository: RepoRepository,private val keyword:String): PagingSource<Int, ReposUIModel>() {

    override fun getRefreshKey(state: PagingState<Int, ReposUIModel>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ReposUIModel> {
        return try {
            val page = params.key ?: 1
            val pageSize = params.loadSize
            val loadState = repository.searchRepo(q = keyword, page = page)
            if(loadState is LoadStatus.Success<*>){
                val items =  loadState.data as List<ReposUIModel>
                val preKey = if (page > 1) page - 1 else null
                val nextKey = if (items.isNotEmpty()) page + 1 else null
                LogUtils.d(TAG,"load data success ${items.joinToString { it.toString() }}")
                LoadResult.Page(items, preKey, nextKey)
            }else{
                LoadResult.Error(java.lang.Exception("no data"))
            }

        } catch (e: Exception) {
            LogUtils.e("MainPageDataSource","load error:${e.message}")
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val TAG = "RepoDataSource"
    }
}