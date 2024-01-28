package com.sbz.collageapplication.admin.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sbz.collageapplication.R
import com.sbz.collageapplication.admin.model.DashboardItemModel
import com.sbz.collageapplication.navigation.Routes
import com.sbz.collageapplication.ui.theme.Almond
import com.sbz.collageapplication.ui.theme.Aquamarine
import com.sbz.collageapplication.ui.theme.BODY_SIZE
import com.sbz.collageapplication.ui.theme.Honeydew
import com.sbz.collageapplication.ui.theme.OuterSpace
import com.sbz.collageapplication.ui.theme.PaleDogwood
import com.sbz.collageapplication.ui.theme.TITLE_SIZE

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminDashboard(navController: NavHostController) {

    val list = listOf(
        DashboardItemModel("Manage Banner", Routes.ManageBanner.route, R.drawable.ic_manage_banner),
        DashboardItemModel(
            "Manage Gallery",
            Routes.ManageGallery.route,
            R.drawable.ic_mange_gallery
        ),
        DashboardItemModel(
            "Manage Faculty",
            Routes.ManageFaculty.route,
            R.drawable.ic_mange_faculty
        ),
        DashboardItemModel(
            "Manage Collage Info",
            Routes.ManageCollageInfo.route,
            R.drawable.ic_manage_collage_info
        )
    )

    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Admin Dashboard",
                        style = TextStyle(
                            color = OuterSpace,
                            fontSize = TITLE_SIZE,
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Aquamarine)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .background(Honeydew)
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                verticalItemSpacing = 4.dp,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(list) {
                    Card(
                        modifier = Modifier
                            .padding(top = 10.dp, start = 10.dp, bottom = 5.dp, end = 5.dp)
                            .clickable {
                                navController.navigate(it.route)
                            },
                        colors = CardDefaults.cardColors(
                            containerColor = PaleDogwood
                        )
                    ) {
                        CardImageItem(
                            imageRes = it.bannerImage,
                            title = it.title
                        )
                    }
                }
            }
        }
    }
}

fun showToast(
    context: Context,
    message: String
) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}


@Composable
fun CardImageItem(
    imageRes: Int,
    title: String
) {
    Column {
        Image(
            painter = painterResource(id = imageRes), contentDescription = null,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(Almond)
                .padding(8.dp),
            contentScale = ContentScale.Inside,
            alignment = Alignment.Center
        )
        Text(
            text = title,
            modifier = Modifier
                .fillMaxWidth()
                .background(PaleDogwood)
                .padding(8.dp),
            style = TextStyle(
                color = OuterSpace,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = BODY_SIZE
            )
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun AdminDashboardPreview() {
    AdminDashboard(navController = rememberNavController())
}