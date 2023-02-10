package ir.kt.github.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import ir.kt.github.R
import ir.kt.github.data.loacl.UserEntity
import ir.kt.github.util.EmptyList
import ir.kt.github.util.ItemUser
import ir.kt.github.util.TopBarCustom
import kotlinx.coroutines.launch

@Composable
fun DetailScreen(navController: NavController, viewModel: DetailViewModel = hiltViewModel()) {
    val detail = viewModel.stateDetail.value
    val isFavorite = viewModel.isFavorite.value
    Box(Modifier.fillMaxSize()) {
        Column(
            Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
        ) {


            detail.detail.let { data ->

                TopBarCustom(
                    data?.login ?: "Detail user",
                    isBack = true,
                    navController = navController
                )

                Column(
                    Modifier
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.primary),
                    horizontalAlignment = CenterHorizontally
                ) {


                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(data?.avatarUrl)
                            .crossfade(true)
                            .build(),
                        placeholder = painterResource(
                            R.drawable.baseline_account_circle_24
                        ),
                        contentDescription = "cover",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(105.dp)
                            .clip(shape = CircleShape)
                            .background(color = Color.Black, shape = CircleShape),
                    )
                    Text(
                        text = data?.name ?: "name",
                        fontSize = 22.sp,
                        fontFamily = FontFamily.Monospace,
                        color = Color.White,
                        maxLines = 1

                    )
                    Text(
                        text = data?.bio ?: "",
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Monospace,
                        color = Color.White,
                        maxLines = 1, modifier = Modifier.padding(horizontal = 10.dp)
                    )

                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .background(color = Color.White, shape = RoundedCornerShape(7.dp))
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(horizontalAlignment = CenterHorizontally) {
                            Text(
                                text = "Followers",
                                color = Color.Black,
                                fontWeight = FontWeight.W400,
                                fontSize = 22.sp,
                                fontFamily = FontFamily.Default
                            )
                            Text(
                                text = if (viewModel.followersList.size!=100) "${viewModel.followersList.size}" else "+100",
                                color = Color.Gray,
                                fontWeight = FontWeight.W400,
                                fontSize = 21.sp,
                                fontFamily = FontFamily.Default
                            )
                        }
                        Column(horizontalAlignment = CenterHorizontally) {
                            Text(
                                text = "Following",
                                color = Color.Black,
                                fontWeight = FontWeight.W400,
                                fontSize = 22.sp,
                                fontFamily = FontFamily.Default
                            )
                            Text(
                                text = if (viewModel.followingList.size!=100) "${viewModel.followingList.size}" else "+100",
                                color = Color.Gray,
                                fontWeight = FontWeight.W400,
                                fontSize = 21.sp,
                                fontFamily = FontFamily.Default
                            )
                        }
                        Column(horizontalAlignment = CenterHorizontally) {
                            Text(
                                text = "Repositories",
                                color = Color.Black,
                                fontWeight = FontWeight.W400,
                                fontSize = 22.sp,
                                fontFamily = FontFamily.Default
                            )
                            Text(
                                text = if (viewModel.countRepos.value!=100) "${viewModel.countRepos.value}" else "+100",
                                color = Color.Gray,
                                fontWeight = FontWeight.W400,
                                fontSize = 21.sp,
                                fontFamily = FontFamily.Default
                            )
                        }
                    }

                }



                TabLayout()


            }


        }

        detail.detail.let { data ->

            FloatingActionButton(
                onClick = {


                    if (!isFavorite) {
                        data?.let {
                            UserEntity(
                                id = data.id,
                                name = data.login,
                                avatarUrl = data.avatarUrl,
                                location = data?.location ?: "not found",
                                company = data?.company ?: "not found",
                                countFollowers = viewModel.followersList.size,
                                countFollowing = viewModel.followingList.size,
                                countRepository = viewModel.countRepos.value
                            )
                        }?.let {
                            viewModel.insertUserInFavorite(it)


                        }


                    } else {
                        viewModel.removeUserInFavorite(data!!.id)

                    }


                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(10.dp)
            ) {
                Icon(
                    imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "",
                    modifier = Modifier.size(35.dp),
                    tint = if (isFavorite) MaterialTheme.colorScheme.tertiary else Color.Gray
                )
            }
        }

    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabLayout() {
    val pagerState = rememberPagerState(2)
    Column(
        modifier = Modifier.background(Color.White)
    ) {
        Tabs(pagerState = pagerState)
        TabsContent(pagerState = pagerState)
    }
}


@ExperimentalPagerApi
@Composable
fun Tabs(pagerState: PagerState) {
    val list = listOf(
        "Followers",
        "Following",
    )

    val scope = rememberCoroutineScope()

    TabRow(
        backgroundColor = MaterialTheme.colorScheme.primary,
        selectedTabIndex = pagerState.currentPage,
        contentColor = Color.White,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions = tabPositions),
                height = 2.dp,
                color = Color.White
            )
        }
    ) {

        list.forEachIndexed { index, _ ->
            Tab(

                text = {
                    Text(
                        list[index],
                        color = if (pagerState.currentPage == index) Color.White else Color.LightGray
                    )
                },

                selected = pagerState.currentPage == index,

                onClick = {
                    // on below line we are specifying the scope.
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }
}


@ExperimentalPagerApi
@Composable
fun TabsContent(pagerState: PagerState, viewModel: DetailViewModel = hiltViewModel()) {

    HorizontalPager(
        state = pagerState,
        count = 2,
        modifier = Modifier
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.background)
    ) {

            page ->
        when (page) {

            0 -> {
                val data = viewModel.followersList
                LazyColumn() {
                    data.let { data ->
                        items(data.size, key = { data[it].id }, itemContent = {
                            data[it].let { it1 ->
                                ItemUser(data = it1) { userName ->

                                }
                            }
                        })
                    }
                }

                if (data.isEmpty()) {
                    EmptyList()

                }
            }

            1 -> {
                val data = viewModel.followingList
                LazyColumn() {
                    data.let { data ->
                        items(data.size, key = { data[it].id }, itemContent = {
                            data[it].let { it1 ->
                                ItemUser(data = it1) { userName ->

                                }
                            }
                        })
                    }
                }


                if (data.isEmpty()) {
                    EmptyList()

                }
            }


        }
    }
}

