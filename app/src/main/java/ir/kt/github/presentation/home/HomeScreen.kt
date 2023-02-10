package ir.kt.github.presentation.home

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import ir.kt.github.util.DotProgress
import ir.kt.github.util.EmptyList
import ir.kt.github.util.HomeDetailGraph
import ir.kt.github.util.ItemUser
import ir.kt.github.util.TopBarCustom

@Composable
fun HomeScreen(navController: NavController , viewModel: HomeViewModel = hiltViewModel()) {

    Column(
        Modifier
            .fillMaxWidth()
        // .padding(horizontal = 10.dp)
    ) {
        // HomeNavGraph(navHostController = rememberNavController())
        TopBarCustom(navController =navController, title = "GitHub Users")
        Row(
            Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.primary)
        ) {
            SearchBar(viewModel)

        }


       // val data = viewModel.getList(viewModel.nameSearch.value).collectAsLazyPagingItems()
        val data = viewModel.stateSearch.value
        LazyColumn(){
            data.let { data ->
                items(data.list.size , key = { data.list[it].id} , itemContent = {
                    data.list[it].let { it1 -> ItemUser(data = it1){userName ->
                        navController.navigate(HomeDetailGraph.Detail.route+"/$userName")
                    } }
                } )
            }
        }

        if (data.loading){
            DotProgress()
        }
        if (data.list.isEmpty()) {
            EmptyList()

        }


    }



}

@Composable
fun SearchBar(viewModel: HomeViewModel) {
    val name = remember {
        mutableStateOf("")
    }

    val nameFocused = remember {
        mutableStateOf(false)
    }

    val nameFieldUpperBorderColor =
        animateColorAsState(
            targetValue =
            if (nameFocused.value)
                Color.Blue
            else
                Color.Gray,
            animationSpec = tween(
                durationMillis = 300,
                easing = LinearEasing
            )
        )

    val nameFieldCaseBorderColor =
        animateColorAsState(
            targetValue =
            if (nameFocused.value)
                Color.Blue
            else
                Color.Gray,
            animationSpec = tween(
                durationMillis = 300,
                easing = LinearEasing
            )
        )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(84.dp)
            .padding(15.dp)
            .clip(shape = RoundedCornerShape(12.dp))
            .background(
                MaterialTheme.colorScheme.background
            )
            .border(
                width = 2.dp,
                brush = Brush.verticalGradient(
                    colors = listOf(
                        nameFieldUpperBorderColor.value,
                        nameFieldCaseBorderColor.value
                    )
                ), shape = RoundedCornerShape(12.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 18.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {


                if (name.value.isEmpty()) {
                    nameFocused.value = false
                    Text(
                        text = "Search User Name ",
                        modifier = Modifier,
                        color = Color.Gray.copy(alpha = 0.7f),
                        fontFamily = FontFamily.Serif,
                        fontSize = 20.sp
                    )
                } else {
                    nameFocused.value = true
                    Icon(imageVector = Icons.Outlined.Search,
                        contentDescription = "search",
                        tint = Color.Gray,
                        modifier = Modifier
                            .clickable {

                                // viewModel.search(name.value)
                                // viewModel.getList(name = name.value)
                                viewModel.searchUser(name = name.value)
                            }
                            .align(
                                Alignment.CenterEnd
                            ))
                }
                BasicTextField(
                    value = name.value,
                    onValueChange = { name.value = it },
                    textStyle = TextStyle(
                        color = MaterialTheme.colorScheme.secondary, fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif, fontSize = 22.sp
                    ),
                    cursorBrush = SolidColor(
                        Color.White
                    )

                )


            }

        }


    }
}