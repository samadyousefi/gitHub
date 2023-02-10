package ir.kt.github.util

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DotProgress() {
    val maxOffset = 30f
    val dotSize = 13.dp // made it bigger for demo
    val delayUnit = 300 // you can change delay to change animation speed
    @Composable
    fun Dot(
        offset: Float
    ) = Spacer(
        androidx.compose.ui.Modifier
            .size(dotSize)
            .offset(x = offset.dp)
            .background(
                color = Color.Blue,
                shape = CircleShape
            )
    )

    val infiniteTransition = rememberInfiniteTransition()

    val offsetLeft by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = delayUnit * 3
                0f at 0 with LinearEasing
                -maxOffset at delayUnit / 2 with LinearEasing
                0f at delayUnit
            }
        )
    )
    val offsetRight by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = delayUnit * 3
                0f at delayUnit with LinearEasing
                maxOffset at delayUnit + delayUnit / 2 with LinearEasing
                0f at delayUnit * 2
            }
        )
    )


    Box(Modifier.fillMaxSize().padding(bottom = 100.dp),  contentAlignment = Alignment.Center) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(horizontal = maxOffset.dp)
        ) {
            val spaceSize = 2.dp

            Dot(offsetLeft)
            Spacer(Modifier.width(spaceSize))
            Dot(0f)
            Spacer(Modifier.width(spaceSize))
            Dot(offsetRight)
        }
    }

}