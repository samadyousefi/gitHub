package ir.kt.github.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import ir.kt.github.data.loacl.UserEntity

@Composable
fun FavoriteUser(user: UserEntity) {
    Card(
        modifier = Modifier
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(),
        shape = RoundedCornerShape(10.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(115.dp)
                .clickable {


                }
                .clip(RoundedCornerShape(10.dp))
//                .background(
//                    brush = Brush.horizontalGradient(
//                        colors = listOf(
//                            Color.Green, Color.Black
//
//                        ),
//                    ), shape = RoundedCornerShape(10.dp)
//                ),
            ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            // val painter = rememberAsyncImagePainter(model = data.fileCover )

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(user.avatarUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(ir.kt.github.R.drawable.baseline_account_circle_24),
                contentDescription = "cover",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(95.dp)
                    .padding(5.dp)
                    .clip(shape = CircleShape)
                //  .background(color = Color.Black, shape = RoundedCornerShape(7.dp)),
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column(horizontalAlignment = Alignment.Start) {
                Text(
                    text = user.name,
                    color = MaterialTheme.colorScheme.secondary,
                    fontWeight = FontWeight.W400,
                    fontSize = 22.sp,
                    fontFamily = FontFamily.Serif
                )
                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (user.location.isNotEmpty()) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "location",
                            tint = Color.Gray.copy(0.5f),
                            modifier = Modifier.size(15.dp)
                        )
                        Text(
                            text = user.location,
                            color = Color.Black,
                            fontWeight = FontWeight.W400,
                            fontSize = 14.sp,
                            fontFamily = FontFamily.Serif
                        )
                    }

                    Spacer(modifier = Modifier.width(5.dp))

                    if (user.company.isNotEmpty()) {
                        Row(
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Work,
                                contentDescription = "location",
                                tint = Color.Gray.copy(0.5f),
                                modifier = Modifier.size(15.dp)
                            )

                            Text(
                                text = user.company,
                                color = Color.Black,
                                fontWeight = FontWeight.W400,
                                fontSize = 14.sp,
                                fontFamily = FontFamily.Serif
                            )
                        }

                    }

                }
                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 5.dp)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Followers",
                            color = Color.Black,
                            fontWeight = FontWeight.W400,
                            fontSize = 16.sp,
                            fontFamily = FontFamily.Default
                        )
                        Text(
                            text = user.countFollowers.toString(),
                            color = MaterialTheme.colorScheme.secondary,
                            fontWeight = FontWeight.W400,
                            fontSize = 16.sp,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Following",
                            color = Color.Black,
                            fontWeight = FontWeight.W400,
                            fontSize = 16.sp,
                            fontFamily = FontFamily.Default
                        )
                        Text(
                            text = user.countFollowing.toString(),
                            color = MaterialTheme.colorScheme.secondary,
                            fontWeight = FontWeight.W400,
                            fontSize = 16.sp,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Repositories",
                            color = Color.Black,
                            fontWeight = FontWeight.W400,
                            fontSize = 16.sp,
                            fontFamily = FontFamily.Default
                        )
                        Text(
                            text = user.countRepository.toString(),
                            color = MaterialTheme.colorScheme.secondary,
                            fontWeight = FontWeight.W400,
                            fontSize = 16.sp,
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }
            }


        }
    }
}