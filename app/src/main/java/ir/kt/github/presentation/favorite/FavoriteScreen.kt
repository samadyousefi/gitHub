package ir.kt.github.presentation.favorite

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import ir.kt.github.R
import ir.kt.github.util.FavoriteUser
import ir.kt.github.util.TopBarCustom

@Composable

fun FavoriteScreen(navController: NavController, viewModel: FavoriteViewModel = hiltViewModel()) {
    Box(Modifier.fillMaxSize()) {

        Column(
            Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
        ) {

            TopBarCustom(
                title = "Favorite Users",
                isBack = true,
                isFav = true,
                navController = navController
            )

            if (viewModel.favoriteList.isNotEmpty()) {
                LazyColumn() {

                    items(
                        count = viewModel.favoriteList.size,
                        key = { viewModel.favoriteList[it].id },
                        itemContent = {
                            FavoriteUser(user = viewModel.favoriteList[it])
                        })
                }
            }
        }


    }
}
