package com.dd2d.ori_android.feature.screen_code.component.code_list_page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dd2d.ori_android.core.model._dummy.DummyCodeList
import com.dd2d.ori_android.core.model.screen_code.CodeListItemModel

@Composable
fun CodeListItem(
    item: CodeListItemModel,
    onClick: (CodeListItemModel) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = { onClick(item) },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 170.dp)
                .padding(24.dp)
        ) {
            Text(text = item.title, fontWeight = FontWeight.Bold, fontSize = 20.sp, maxLines = 1)
            Spacer(modifier = Modifier.heightIn(10.dp))
            Text(text = item.description, fontWeight = FontWeight.W400, fontSize = 14.sp, maxLines = 8)
        }
    }
}

@Preview
@Composable
private fun CodeListItemPrev() {
    CodeListItem(
        item = DummyCodeList.first(),
        onClick = {},
        modifier = Modifier
    )
}