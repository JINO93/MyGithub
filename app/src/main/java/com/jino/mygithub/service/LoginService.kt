package com.jino.mygithub.service

import com.jino.mygithub.bean.GHAccessToken
import retrofit2.Response
import retrofit2.http.*

interface LoginService {

    @GET("https://github.com/login/oauth/access_token")
    @Headers("Accept: application/json")
    fun authorizationsCode(
        @Query("client_id") client_id: String,
        @Query("client_secret") client_secret: String,
        @Query("code") code: String
    ): Response<GHAccessToken>
}