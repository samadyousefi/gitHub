package ir.kt.github.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import ir.kt.github.data.remote.dto.ResponseSearchUsers

@Composable
fun ItemUser(data: ResponseSearchUsers.ItemUser , itemClick:(String) ->Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp), elevation = CardDefaults.cardElevation(), shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(95.dp)
                .clickable {

                    itemClick(data.login)
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
           verticalAlignment = Alignment.CenterVertically , horizontalArrangement = Arrangement.Start
        ) {

            // val painter = rememberAsyncImagePainter(model = data.fileCover )

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data.avatarUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(ir.kt.github.R.drawable.baseline_account_circle_24),
                contentDescription = "cover",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(7.dp))
                    .size(95.dp)
                    .clip(shape = RoundedCornerShape(7.dp))
                  //  .background(color = Color.Black, shape = RoundedCornerShape(7.dp)),
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = data.login,
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.W400,
                fontSize = 25.sp,
                fontFamily = FontFamily.Cursive
            )



        }
    }
}