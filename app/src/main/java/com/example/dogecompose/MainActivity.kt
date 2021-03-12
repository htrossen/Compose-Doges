package com.example.dogecompose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.dogecompose.data.dogeList
import com.example.dogecompose.ui.theme.DogeComposeTheme


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DogeComposeTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "list") {
                    composable("list") {
                        DogeListingPage(
                            doges = dogeList,
                            navToDetails = { doge ->
                                navController.navigate(route = "details/${doge.name}")
                            }
                        )
                    }
                    composable("details/{name}") { backStackEntry ->
                        val name = backStackEntry.arguments?.getString("name")
                        val doge = dogeList.find { it.name == name }
                        DogeDetailPage(
                            doge = doge,
                            navBack = { navController.popBackStack() }
                        )
                    }
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DogeComposeTheme {
        DogeListingPage(dogeList, {})
    }
}