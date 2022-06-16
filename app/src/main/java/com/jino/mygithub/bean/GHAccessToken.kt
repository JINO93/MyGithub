package com.jino.mygithub.bean

/**
 * 登录成功返回的token数据
 */
data class GHAccessToken(
    var id: Int = 0,
    var url: String? = null,
    var token: String? = null,
    var access_token: String? = null
)