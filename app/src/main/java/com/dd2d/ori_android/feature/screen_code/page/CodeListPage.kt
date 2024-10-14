package com.dd2d.ori_android.feature.screen_code.page

import android.util.Log
import androidx.compose.animation.core.snap
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dd2d.ori_android.core.model._dummy.DummyCodeList
import com.dd2d.ori_android.core.model.screen_code.CodeListItemModel
import com.dd2d.ori_android.feature._common.theme.MainColor
import com.dd2d.ori_android.feature.screen_code.CodeListPageState
import com.dd2d.ori_android.feature.screen_code.component.code_list_page.CodeListItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CodeListPage(
    list: SnapshotStateList<CodeListItemModel>,
    listState: CodeListPageState,
    onClickItem: (CodeListItemModel) -> Unit,
    requestRefresh: () -> Unit,
    requestNextPage: () -> Unit,
    modifier: Modifier = Modifier
) {
    val refreshState = rememberPullToRefreshState()
    val isRefreshing by remember { mutableStateOf(false) }

    val lazyState = rememberLazyListState()
    val requireNextPage by remember {
        derivedStateOf {
            lazyState.firstVisibleItemIndex >= list.size * 0.8
                    && list.size > 0
        }
    }

    LaunchedEffect(requireNextPage) {
        if(requireNextPage) {
            requestNextPage()
        }
    }


    PullToRefreshBox(
        state = refreshState,
        isRefreshing = isRefreshing,
        onRefresh = requestRefresh,
        modifier = modifier
    ) {
        LazyColumn(
            state = lazyState,
            verticalArrangement = Arrangement.spacedBy(15.dp),
            modifier = Modifier
                .matchParentSize()
                .padding(horizontal = 20.dp)
        ) {
            item(key = "top-space") {
                Spacer(modifier = Modifier.height(10.dp))
            }

            items(
                items = list,
                key = { item -> item.id }
            ) {
                CodeListItem(
                    item = it,
                    onClick = onClickItem,
                    modifier = Modifier
                        .animateItem()
                )
            }

            if(listState is CodeListPageState.Loading) {
                item(key = "loading") {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        CircularProgressIndicator(
                            color = MainColor,
                            trackColor = MainColor.copy(alpha = 0.5f)
                        )
                    }
                }
            }
            item(key = "bottom-space") {
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CodeListPagePrev() {
    val list = remember { mutableStateListOf<CodeListItemModel>() }
    var state by remember { mutableStateOf<CodeListPageState>(CodeListPageState.Loading) }
    LaunchedEffect(Unit) {
        delay(2000L)
        list.addAll(DummyCodeList)
        state = CodeListPageState.Success(false)
    }
    CodeListPage(
        list = list,
        listState = state,
        onClickItem = {},
        requestNextPage = {},
        requestRefresh = {},
        modifier = Modifier
            .fillMaxSize()
    )
}