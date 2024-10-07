package com.dd2d.ori_android.core.data.remote._common

/** 원격 저장소에 저장된 데이터를 가져오는 상태
 *
 * @see RemoteDataState.Loading
 * @see RemoteDataState.Error
 * @see RemoteDataState.Success*/
sealed interface RemoteDataState <out T> {
    data object Loading: RemoteDataState<Nothing>
    data class Error(val exception: Throwable): RemoteDataState<Nothing>
    data class Success <T>(val data: T): RemoteDataState<T>
}