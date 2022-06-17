package com.jino.mygithub.feature.main

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jino.mygithub.PAGE_SIZE
import com.jino.mygithub.Repository.RepoRepository
import com.jino.mygithub.base.BaseViewModel
import com.jino.mygithub.bean.ui.ReposUIModel
import com.jino.mygithub.common.RetrofitManager
import com.jino.mygithub.datasource.MainPageDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainPageViewModel @Inject constructor():BaseViewModel() {

    private val repoRepository: RepoRepository = RepoRepository(RetrofitManager())

    fun fetchMainPageData(): Flow<PagingData<ReposUIModel>> {
        return Pager(
            config = PagingConfig(PAGE_SIZE),
            pagingSourceFactory = {MainPageDataSource(repoRepository)}
        ).flow
            .cachedIn(viewModelScope)
    }
}