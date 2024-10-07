package com.dd2d.ori_android.feature.screen_code

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.dd2d.ori_android.feature.screen_code.page.CodeDetailPage
import com.dd2d.ori_android.feature.screen_code.page.CodeListPage

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun CodeScreen(
    modifier: Modifier = Modifier
) {
    val scaffoldState = rememberListDetailPaneScaffoldNavigator<Long>()

    val viewModel: CodeScreenViewModel = hiltViewModel()
    val list = viewModel.list
    val listState by viewModel.listState.collectAsState()

    ListDetailPaneScaffold(
        directive = scaffoldState.scaffoldDirective,
        value = scaffoldState.scaffoldValue,
        listPane = {
            AnimatedPane {
                CodeListPage(
                    list = list,
                    listState = listState,
                    onClickItem = { item ->
                        scaffoldState.navigateTo(pane = ListDetailPaneScaffoldRole.Detail, item.id)
                    },
                    requestRefresh = viewModel::refresh,
                    requestNextPage = viewModel::nextPage,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        },
        detailPane = {

            AnimatedPane {
                CodeDetailPage(
                    onBack = scaffoldState::navigateBack,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        },
        modifier = modifier
    )

}

@Preview
@Composable
private fun CodeScreenPrev() {
    CodeScreen(
        modifier = Modifier
            .fillMaxSize()
    )
}