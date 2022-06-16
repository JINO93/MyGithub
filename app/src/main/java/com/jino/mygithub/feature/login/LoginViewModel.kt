package com.jino.mygithub.feature.login

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.jino.mygithub.Repository.LoginRepository
import com.jino.mygithub.base.BaseViewModel
import com.jino.mygithub.base.LoadStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginRepository: LoginRepository):BaseViewModel() {

    // 是否登录成功
    private val mLoginSuccess = MutableLiveData<Boolean>()
    val loginSuccess :LiveData<Boolean> = mLoginSuccess

    // 数据请求状态
    private val mLoadStatus= MutableLiveData<LoadStatus<Boolean>>()
    val loadStatus :LiveData<LoadStatus<Boolean>> = mLoadStatus

    fun oauth(context: Context, code: String) {
        viewModelScope.launch {
            mLoadStatus.postValue(LoadStatus.Loading())
            loginRepository.auth(code).collect {
                val ret = it.data ?: false
                mLoginSuccess.value = ret
                mLoadStatus.value = LoadStatus.Success(ret)
            }
        }
    }
}