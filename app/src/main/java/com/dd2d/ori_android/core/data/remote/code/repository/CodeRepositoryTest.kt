package com.dd2d.ori_android.core.data.remote.code.repository

import android.content.res.Resources.NotFoundException
import com.dd2d.ori_android.core.data.remote._common.RemoteDataState
import com.dd2d.ori_android.core.data.remote.code.request.CodeCreateRequestDTO
import com.dd2d.ori_android.core.data.remote.code.request.CodeSearchRequestDTO
import com.dd2d.ori_android.core.data.remote.code.response.CodeDetailResponseDTO
import com.dd2d.ori_android.core.data.remote.code.response.CodeListItemResponseDTO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import java.time.LocalDateTime
import javax.inject.Inject

private object DummyRemoteServer {
    fun getList(options: CodeSearchRequestDTO): List<CodeListItemResponseDTO> = codeList
        .subList(
            fromIndex = (options.page - 1) * options.take,
            toIndex = (options.page) * options.take
        )
        .map { detail ->
            CodeListItemResponseDTO(
                id = detail.id,
                title = detail.title,
                description = detail.description,
                level = detail.level,
                reviewTime = detail.reviewTime,
                code = detail.code,
                createdAt = detail.createdAt,
                updatedAt = detail.updatedAt
            )
        }

    fun getDetail(id: Long): CodeDetailResponseDTO? = codeList.find { it.id == id }

    fun create(request: CodeCreateRequestDTO) {
        codeList.add(
            CodeDetailResponseDTO(
                id = codeList.size.toLong(),
                title = request.title,
                description = request.description,
                level = request.level,
                reviewTime = request.reviewTime,
                code = request.code,
                createdAt = LocalDateTime.now().toString(),
                updatedAt = LocalDateTime.now().toString(),
            )
        )
    }

    val codeList = List(200) { index ->
        CodeDetailResponseDTO(
            id = index.toLong(),
            title = "제목 $index",
            description = "설명 $index",
            level = (1..5).random(),
            reviewTime = "10분",
            code = "코드 $index",
            createdAt = LocalDateTime.now().toString(),
            updatedAt = LocalDateTime.now().toString(),
        )
    }.toMutableList()
}


class CodeRepositoryTest @Inject constructor(): CodeRepository {
    override fun getList(options: CodeSearchRequestDTO): Flow<RemoteDataState<List<CodeListItemResponseDTO>>> = flow {
        val response = DummyRemoteServer.getList(options)

        delay(500L)
        when((1..20).random()) {
            in 1..2 -> throw Exception("로컬 에러 발생")
            in 3..4 -> emit(RemoteDataState.Error(Exception("서버 에러 발생")))
            else -> emit(RemoteDataState.Success(response))
        }
    }
        .onStart { emit(RemoteDataState.Loading) }
        .catch { emit(RemoteDataState.Error(it)) }

    override fun getDetail(id: Long): Flow<RemoteDataState<CodeDetailResponseDTO>>  = flow {
        val response = DummyRemoteServer.getDetail(id) ?: throw NotFoundException("대상을 찾을 수 없음.")

        delay(1000L)
        when((1..20).random()) {
            in 1..2 -> throw Exception("로컬 에러 발생")
            in 3..4 -> emit(RemoteDataState.Error(Exception("서버 에러 발생")))
            else -> emit(RemoteDataState.Success(response))
        }
    }
        .onStart { emit(RemoteDataState.Loading) }
        .catch { emit(RemoteDataState.Error(it)) }

    override suspend fun create(request: CodeCreateRequestDTO): Flow<RemoteDataState<Unit>> {
        TODO("Not yet implemented")
    }
}