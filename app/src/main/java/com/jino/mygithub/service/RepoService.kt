package com.jino.mygithub.service

import com.jino.mygithub.PAGE_SIZE
import com.shuyu.github.kotlin.model.bean.Repository
import com.shuyu.github.kotlin.model.bean.SearchResult
import retrofit2.Response
import retrofit2.http.*

interface RepoService {

    @GET("https://github.com/trending/{languageType}")
    @Headers("Content-Type: text/plain;charset=utf-8")
    fun getTrendData(
        @Header("forceNetWork") forceNetWork: Boolean,
        @Path("languageType") languageType: String,
        @Query("since") since: String): Response<String>

    @GET("search/repositories")
    suspend fun searchRepos(
        @Query(value = "q", encoded = true) query: String,
        @Query("sort") sort: String = "best%20match",
        @Query("order") order: String = "desc",
        @Query("page") page: Int,
        @Query("per_page") per_page: Int = PAGE_SIZE
    ): Response<SearchResult<Repository>>
}