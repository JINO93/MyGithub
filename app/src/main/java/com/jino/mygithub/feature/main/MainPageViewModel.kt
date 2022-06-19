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
import com.jino.mygithub.datasource.RepoDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainPageViewModel @Inject constructor(private val repoRepository: RepoRepository):BaseViewModel() {


    fun fetchMainPageData(): Flow<PagingData<ReposUIModel>> {
        return Pager(
            config = PagingConfig(PAGE_SIZE),
            pagingSourceFactory = {RepoDataSource(repoRepository,"Android")}
        ).flow
            .cachedIn(viewModelScope)
    }
}