package com.sbz.collageapplication.itemview

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.sbz.collageapplication.model.BannerModel
import com.sbz.collageapplication.ui.theme.BODY_SIZE
import com.sbz.collageapplication.ui.theme.Red

@Composable
fun ManageBannerItemView(
    bannerModel: BannerModel,
    delete: (docId: String) -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 10.dp)
            .wrapContentHeight()
    ) {
        Column(
            modifier = Modifier.wrapContentSize()
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = bannerModel.url),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(
                        RoundedCornerShape(
                            bottomEnd = 10.dp,
                            bottomStart = 10.dp
                        )
                    ),
                contentScale = ContentScale.FillBounds,
            )

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        delete(bannerModel.docId!!)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 10.dp, top = 10.dp, bottom = 10.dp, start = 5.dp),
                    colors = ButtonDefaults.buttonColors(
                        Red
                    )
                ) {
                    Text(
                        text = "Delete",
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = BODY_SIZE,
                            color = Color.Black
                        )
                    )
                }
            }
        }
    }
}