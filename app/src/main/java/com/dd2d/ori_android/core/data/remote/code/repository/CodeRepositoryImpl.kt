package com.dd2d.ori_android.core.data.remote.code.repository

import com.dd2d.ori_android.core.data.remote._common.RemoteDataState
import com.dd2d.ori_android.core.data.remote.code.request.CodeCreateRequestDTO
import com.dd2d.ori_android.core.data.remote.code.request.CodeSearchRequestDTO
import com.dd2d.ori_android.core.data.remote.code.response.CodeDetailResponseDTO
import com.dd2d.ori_android.core.data.remote.code.response.CodeListItemResponseDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CodeRepositoryImpl @Inject constructor(

): CodeRepository {
    override fun getList(options: CodeSearchRequestDTO): Flow<RemoteDataState<List<CodeListItemResponseDTO>>> = flow {

    }

    override fun getDetail(id: Long): Flow<RemoteDataState<CodeDetailResponseDTO>> {
        TODO("Not yet implemented")
    }

    override suspend fun create(request: CodeCreateRequestDTO): Flow<RemoteDataState<Unit>> {
        TODO("Not yet implemented")
    }
}