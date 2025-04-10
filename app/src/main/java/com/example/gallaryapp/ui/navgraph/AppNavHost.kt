package com.example.gallaryapp.ui.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.gallaryapp.ui.gallery.album.AlbumListScreen
import com.example.gallaryapp.ui.gallery.imagelist.ImageListScreen


@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.Album.route) {
        composable(Screen.Album.route) {
            AlbumListScreen(navController)
        }
        composable(
            route = Screen.ImageList.route,
            arguments = listOf(navArgument("albumName") { type = NavType.StringType })
        ) { backStackEntry ->
            val albumName = backStackEntry.arguments?.getString("albumName")
            ImageListScreen(albumName = albumName, onBackPressed = {
                navController.popBackStack()
            })
        }
    }
}
