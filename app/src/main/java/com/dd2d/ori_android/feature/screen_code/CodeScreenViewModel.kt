package com.dd2d.ori_android.feature.screen_code

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dd2d.ori_android.core.data.remote._common.RemoteDataState
import com.dd2d.ori_android.core.data.remote.code.repository.CodeRepository
import com.dd2d.ori_android.core.model.screen_code.CodeListItemModel
import com.dd2d.ori_android.core.model.screen_code.CodeSearchModel
import com.dd2d.ori_android.core.model.screen_code.mapModel
import com.dd2d.ori_android.core.model.screen_code.toDTO
import com.dd2d.ori_android.core.model.screen_code.toModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import kotlin.time.Duration.Companion.minutes

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
internal class CodeScreenViewModel @Inject constructor(
    codeRepository: CodeRepository
): ViewModel() {
    private val listSearchOptions = MutableStateFlow(CodeSearchModel(page = 1, take = 100))
    val list = mutableStateListOf<CodeListItemModel>()
    fun refresh() {
        list.clear()
        listSearchOptions.update { prev ->
            prev.copy(page = 1)
        }
    }
    fun nextPage() {
        val currentState = listState.value
        if(currentState is CodeListPageState.Success && !currentState.isLastPage) {
            listSearchOptions.update { prev ->
                prev.copy(page = prev.page + 1)
            }
        }
    }
    val listState = listSearchOptions
        .flatMapLatest { options ->
            codeRepository.getList(options.toDTO())
        }
        .onCompletion { list.clear() }
        .map { state ->
            when(state) {
                is RemoteDataState.Loading -> CodeListPageState.Loading
                is RemoteDataState.Error -> CodeListPageState.Error(state.exception)
                is RemoteDataState.Success -> {
                    val newList = state.data.mapModel()
                    list.addAll(newList)
                    CodeListPageState.Success(isLastPage = newList.isEmpty())
                }
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5.minutes.inWholeMilliseconds),
            initialValue = CodeListPageState.Loading
        )


    private val detailOptions = MutableStateFlow<Long?>(null)
    fun setDetailOption(id: Long) {
        detailOptions.update { id }
    }
    val detailState = detailOptions
        .flatMapLatest { id ->
            id?.let {
                codeRepository.getDetail(id)
            }?: run {
                emptyFlow()
            }
        }
        .map { state ->
            when(state) {
                is RemoteDataState.Loading -> CodeDetailPageState.Loading
                is RemoteDataState.Error -> CodeDetailPageState.Error(state.exception)
                is RemoteDataState.Success -> CodeDetailPageState.Success(state.data.toModel())
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = CodeDetailPageState.Loading
        )

}