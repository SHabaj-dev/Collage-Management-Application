package com.sbz.collageapplication.admin.screens

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.sbz.collageapplication.itemview.ManageBannerItemView
import com.sbz.collageapplication.ui.theme.Almond
import com.sbz.collageapplication.ui.theme.BODY_SIZE
import com.sbz.collageapplication.ui.theme.LightBlue
import com.sbz.collageapplication.ui.theme.OuterSpace
import com.sbz.collageapplication.ui.theme.PaleDogwood
import com.sbz.collageapplication.ui.theme.Red
import com.sbz.collageapplication.viewmodel.BannerViewModel
import com.sbz.collageapplication.widget.TopAppBarWidget

@Composable
fun ManageBanner(
    navController: NavController
) {

    val context = LocalContext.current

    val bannerViewModel: BannerViewModel = BannerViewModel()
    val isUploaded by bannerViewModel.isPosted.observeAsState(false)
    val isDeleted by bannerViewModel.isDeleted.observeAsState(false)
    val bannerList by bannerViewModel.bannerList.observeAsState(null)

    bannerViewModel.getBanner()

    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {
        imageUri = it
    }

    LaunchedEffect(isUploaded) {
        if (isUploaded) {
            Toast.makeText(context, "Image Uploaded Successfully", Toast.LENGTH_SHORT).show()
            imageUri = null
        }
    }

    LaunchedEffect(isDeleted) {
        if (isDeleted) {
            Toast.makeText(context, "Image Deletion Successfully", Toast.LENGTH_SHORT).show()
        }
    }


    Scaffold(topBar = {
        TopAppBarWidget(
            title = "Manage Banners",
            backgroundColor = PaleDogwood,
            onClick = { navController.navigateUp() },
            isUpEnable = true
        )
    }, floatingActionButton = {
        FloatingActionButton(
            onClick = {
                launcher.launch("image/*")
            }, shape = CircleShape, containerColor = OuterSpace
        ) {
            Icon(
                imageVector = Icons.Rounded.Add, contentDescription = null, tint = Color.White
            )
        }
    }, content = { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Almond)
                .padding(paddingValues)
        ) {
            if (imageUri != null) {
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 10.dp)
                        .wrapContentHeight()
                ) {
                    Column(
                        modifier = Modifier.wrapContentSize()
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(model = imageUri),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(220.dp)
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

                                    bannerViewModel.saveImage(imageUri!!)

                                },
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(
                                        end = 5.dp,
                                        top = 10.dp,
                                        bottom = 10.dp,
                                        start = 10.dp
                                    ),
                                colors = ButtonDefaults.buttonColors(
                                    LightBlue
                                )
                            ) {
                                Text(
                                    text = "Upload",
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = BODY_SIZE,
                                        color = Color.Black
                                    )
                                )
                            }

                            Button(
                                onClick = {
                                    imageUri = null
                                },
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(
                                        end = 10.dp,
                                        top = 10.dp,
                                        bottom = 10.dp,
                                        start = 5.dp
                                    ),
                                colors = ButtonDefaults.buttonColors(
                                    Red
                                )
                            ) {
                                Text(
                                    text = "Cancel",
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

            LazyColumn {
                items(bannerList ?: emptyList()) {
                    ManageBannerItemView(
                        bannerModel = it,
                        delete = { docId ->
                            bannerViewModel.deleteBanner(docId)
                        })
                }
            }
        }
    })

}


@Preview(showSystemUi = true)
@Composable
fun ManageBannerPreview() {
    ManageBanner(navController = rememberNavController())
}