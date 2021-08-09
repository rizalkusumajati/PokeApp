package com.example.pokeapp.data.api.models

sealed class ApiResult<out T>{
    data class OnSuccess<out T>(val response: T): ApiResult<T>()
    data class OnFailure(val code: Int? = null, val errorMessage: String? = null): ApiResult<Nothing>()
    data class NetworkError(val message: String? = null) : ApiResult<Nothing>()
}
