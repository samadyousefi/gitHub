package ir.kt.github.util

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun TopBarCustom(
    title: String,
    isBack: Boolean = false,
    isFav:Boolean = false,
    navController: NavController,
) {


    Column(Modifier.fillMaxWidth()) {

        Row(
            Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(MaterialTheme.colorScheme.primary)
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (isBack) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier
                            .clip(
                                CircleShape
                            )
                            .padding(horizontal = 8.dp)
                            .clickable {
                                navController.popBackStack()
                            },
                        tint = Color.White
                    )
                }


                Text(
                    text = title,
                    fontSize = 22.sp,
                    fontFamily = FontFamily.Monospace,
                    color = Color.White
                )
            }

            if (!isFav) {
                Row(horizontalArrangement = Arrangement.SpaceAround) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Favorite",
                        modifier = Modifier
                            .clip(
                                CircleShape
                            )
                            .clickable {
                                navController.navigate(HomeDetailGraph.Favorite.route)
                            },
                        tint = Color.Gray
                    )
                    Spacer(modifier = Modifier.width(35.dp))


                }

            }
        }


    }

}