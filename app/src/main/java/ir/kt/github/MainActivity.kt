package ir.kt.github

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Light
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material.icons.filled.NetworkCell
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ir.kt.github.presentation.ThemeViewModel
import ir.kt.github.ui.theme.GithubTheme
import ir.kt.github.util.HomeDetailGraph
import ir.kt.github.util.HomeNavGraph
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var application: App


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent {
            val navController : NavHostController = rememberNavController()
          //  RootNavGraph(navController = navController)
        //   val scope = rememberCoroutineScope()
            val themeViewModel: ThemeViewModel = hiltViewModel()

            GithubTheme(themeViewModel.isDark.value) {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Box(contentAlignment = Alignment.TopCenter) {
                        HomeNavGraph(navHostController = navController)

                        Icon(imageVector = if (themeViewModel.isDark.value) Icons.Default.LightMode else Icons.Default.DarkMode,
                            contentDescription = "Theme",
                            modifier = Modifier.padding(2.dp).size(30.dp)
                                .offset(x = (-5).dp,y = 10.dp)
                                .clickable {

                                    themeViewModel.toggleLightTheme()

                                }.align(Alignment.TopEnd),
                            tint = Color.Gray )

                    }

                }
            }
        }
    }

}


