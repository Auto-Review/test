package com.dd2d.ori_android.feature.screen_code.page

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dd2d.ori_android.core.model._dummy.DummyCode
import com.dd2d.ori_android.core.model.screen_code.CodeDetailModel
import com.dd2d.ori_android.feature._common.dialog.ErrorDialog
import com.dd2d.ori_android.feature._common.dialog.LoadingDialog
import com.dd2d.ori_android.feature.screen_code.CodeDetailPageState
import java.io.IOException

@Composable
internal fun CodeDetailPage(
    detailState: CodeDetailPageState,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BackHandler(onBack = onBack)

    Box(modifier = modifier) {
        when(detailState) {
            is CodeDetailPageState.Loading -> LoadingDialog(modifier = modifier)
            is CodeDetailPageState.Error -> {
                ErrorDialog(
                    exception = detailState.exception,
                    onDismiss = onBack,
                )
            }
            is CodeDetailPageState.Success -> {
                CodeDetail(
                    data = detailState.data,
                    onBack = onBack,
                    modifier = modifier
                )
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CodeDetail(
    data: CodeDetailModel,
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = data.title) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = "뒤로 가기")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )
        },
        containerColor = Color.White,
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .consumeWindowInsets(innerPadding)
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp, vertical = 15.dp)
                .verticalScroll(state = scrollState)
        ) {
            Text(
                text = data.code,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Text(text = data.createdAt, textAlign = TextAlign.End, modifier = Modifier.fillMaxWidth())
            HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp))
            Text(
                text = data.description,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp
            )
        }
    }
}

@Preview
@Composable
private fun CodeDetailPagePrev() {
    CodeDetailPage(
//        detailState = CodeDetailPageState.Loading,
//        detailState = CodeDetailPageState.Error(IOException("네트워크 에러")),
        detailState = CodeDetailPageState.Success(DummyCode),
        onBack = {},
        modifier = Modifier
            .fillMaxSize()
    )
}