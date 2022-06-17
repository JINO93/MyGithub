package com.jino.mygithub.base

import retrofit2.Response

/**
 * 加载状态
 */
sealed class LoadStatus<T>(
        val data: T? = null,
        val errorCode: Int? = null
) {
    class Success<T>(data: T) : LoadStatus<T>(data)
    class Loading<T>(data: T? = null) : LoadStatus<T>(data)
    class DataError<T>(errorCode: Int) : LoadStatus<T>(null, errorCode)

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is DataError -> "Error[exception=$errorCode]"
            is Loading<T> -> "Loading"
        }
    }
}


inline  fun <T> transformResponseToLoadStatus(response:Response<T>):LoadStatus<T>{
    if(response.isSuccessful){
        if (response.body() != null) {
            return LoadStatus.Success(response.body()!!)
        }else{
            return LoadStatus.DataError<T>(response.code())
        }
    }else{
        return LoadStatus.DataError<T>(response.code())
    }
}
