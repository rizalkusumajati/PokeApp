package com.example.pokeapp.util

import android.util.Log
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.pokeapp.data.api.models.ApiResult
import com.example.pokeapp.domain.models.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException


inline suspend fun <T> safeApiCall(crossinline apiCall: suspend () -> T): ApiResult<T> {
    return withContext(Dispatchers.IO) {
        try {
            ApiResult.OnSuccess(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> ApiResult.NetworkError(throwable.message)
                is HttpException -> {
                    val code = throwable.code()
                    ApiResult.OnFailure(code = code, errorMessage = throwable.message())
                }
                else -> {
                    Log.e("ERROR", throwable.message ?: "")
                    ApiResult.OnFailure(null, null)
                }
            }
        }
    }
}


inline fun <T, A, DATA> performGetSingleDataSource(
    crossinline databaseQuery: suspend () -> T,
    crossinline networkCall: suspend () -> ApiResult<A>,
    crossinline converterToEntity: (A) -> T,
    crossinline saveCallResult: suspend (T) -> Unit,
    crossinline converterToDomain: (T) -> DATA
): Flow<UIState<DATA>> = flow {
    try {
        var isEmpty = true
        emit(UIState.Loading)
        val localData = databaseQuery.invoke()
        localData?.let {
            emit(UIState.SuccessFromCached(converterToDomain.invoke(localData)))
            isEmpty = false
        }
        val apiResult = networkCall.invoke()
        when (apiResult) {
            is ApiResult.OnSuccess -> {
                saveCallResult.invoke(converterToEntity(apiResult.response))
                databaseQuery.invoke()?.let {
                    emit(UIState.SuccessFromRemote(converterToDomain.invoke(databaseQuery.invoke())))
                } ?: run {
                    if (isEmpty) {
                        emit(UIState.Empty)
                        Log.d("SINGLE", "CALL 21")
                    }
                }

            }
            is ApiResult.OnFailure -> {
                emit(UIState.Error(apiResult.errorMessage))

            }
            is ApiResult.NetworkError -> {
                emit(UIState.Error(apiResult.message))
            }
        }

    } catch (exception: Exception) {
        Log.d("Ex", exception.message ?: "")
    }
}


inline suspend fun <T, A> performGetRemoteDataSource(
    crossinline networkCall: suspend () -> ApiResult<A>,
    crossinline converterToEntity: (A) -> T,
    crossinline saveCallResult: suspend (T) -> Unit,
): ApiResult<A> {
    try {
        var isEmpty = true
        val apiResult = networkCall.invoke()
        when (apiResult) {
            is ApiResult.OnSuccess -> {
                saveCallResult.invoke(converterToEntity(apiResult.response))
            }
            is ApiResult.OnFailure -> {

            }
            is ApiResult.NetworkError -> {
            }
        }

        return apiResult

    } catch (exception: Exception) {
        Log.d("Ex", exception.message ?: "")

        return ApiResult.OnFailure(errorMessage = exception.message)
    }
}

infix fun AppCompatImageView.loadImageUrl(url: Any?) =
    Glide.with(context)
        .load(url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
        .clearOnDetach()
