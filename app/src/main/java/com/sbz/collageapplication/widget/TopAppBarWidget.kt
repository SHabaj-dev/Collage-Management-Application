package com.sbz.collageapplication.widget

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import com.sbz.collageapplication.ui.theme.TITLE_SIZE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWidget(
    title: String,
    textColor: Color = Color.Black,
    backgroundColor: Color,
    fontSize: TextUnit = TITLE_SIZE,
    fontWeight: FontWeight = FontWeight.Bold,
    isUpEnable: Boolean = false,
    onClick: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = TextStyle(
                    color = textColor,
                    fontSize = fontSize,
                    fontWeight = fontWeight
                )
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = backgroundColor),
        navigationIcon = {
            if (isUpEnable) {
                IconButton(
                    onClick = onClick
                ) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = null,
                        tint = textColor
                    )
                }
            }
        }
    )
}