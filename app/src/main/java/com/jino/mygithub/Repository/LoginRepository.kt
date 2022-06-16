package com.jino.mygithub.Repository

import com.jino.mygithub.BuildConfig
import com.jino.mygithub.SP_GH_ACCESSTOKEN
import com.jino.mygithub.base.LoadStatus
import com.jino.mygithub.bean.GHAccessToken
import com.jino.mygithub.common.RetrofitManager
import com.jino.mygithub.service.LoginService
import com.jino.mygithub.util.PreferenceObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import retrofit2.Response
import javax.inject.Inject

class LoginRepository @Inject constructor(private val retrofitManager: RetrofitManager) {

    private var loginService: LoginService = retrofitManager.createService(LoginService::class.java)

    private var authedSp by PreferenceObject(SP_GH_ACCESSTOKEN,"")

    suspend fun auth(code:String): Flow<LoadStatus<Boolean>> {
        return flow {
            emit(loginService.authorizationsCode(
                BuildConfig.CLIENT_ID,
                BuildConfig.CLIENT_SECRET,
                code
            ))
        }.flowOn(Dispatchers.IO)
            .map {
                if(it.isSuccessful){
                    val ghAccessToken = it.body()
                    if(ghAccessToken?.access_token?.isNotBlank() == true){
                        LoadStatus.Success(true)
                    }else{
                        LoadStatus.Success(false)
                    }
                }else{
                    LoadStatus.DataError(it.code())
                }
            }
    }
}