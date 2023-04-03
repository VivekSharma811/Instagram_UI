package com.example.instagram

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.instagram.Screens.HOME
import com.example.instagram.Screens.SIGN_UP

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Screens.Splash) {
                composable(route = Screens.Splash) {
                    SplashScreen(navController = navController)
                }
                composable(route = Screens.Login) {
                    LoginScreen(navController = navController)
                }
                composable(route = SIGN_UP) {
                    SignUpScreen(navController = navController)
                }
                composable(route = HOME) {
                    HomeScreen(navController = navController)
                }
                composable(route = Screens.Profile) {
                    ProfileScreen()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
}