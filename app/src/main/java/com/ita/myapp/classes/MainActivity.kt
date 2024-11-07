package com.ita.myapp.classes

import android.graphics.fonts.FontStyle
import android.os.Bundle
import android.view.Menu
import android.view.WindowInsets
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ita.myapp.classes.ui.theme.Myapp2Theme
import kotlin.math.max
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ita.myapp.classes.ui.screens.APisScreen
import com.ita.myapp.classes.ui.screens.HomeScreen
import com.ita.myapp.classes.ui.screens.ComponentsScreen
import com.ita.myapp.classes.ui.screens.MenuScreen
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import com.google.accompanist.insets.ProvideWindowInsets
import com.ita.myapp.classes.ui.location.MapsSearchView
import com.ita.myapp.classes.ui.screens.LoginScreen


//import androidx.navigation.compose.NavHostController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Colors also baterry and stuff bar
        setContent {
            ProvideWindowInsets{
                ComposeMultiScreenApp()
            }
            }
    }
@Composable
fun ComposeMultiScreenApp() {
    val navController = rememberNavController()
    Surface(
        color = Color.White,
        modifier = Modifier.statusBarsPadding()
    ) {
        SetupNavGraph(navController = navController)
    }
}

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "login") {
        composable("menu") { MenuScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("components") { ComponentsScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("apis") { APisScreen(navController) }
        composable("MapsSearchView/{lat}/{long}/{address}") { backStackEntry ->
            val lat = backStackEntry.arguments?.getString("lat")?.toDouble() ?: 0.0
            val long = backStackEntry.arguments?.getString("long")?.toDouble() ?: 0.0
            val address = backStackEntry.arguments?.getString("address") ?: ""
            MapsSearchView(lat, long, address)
        }
    }
}
}