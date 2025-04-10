package com.example.comeragallary.ui.navgraph

sealed class Screen(val route: String) {
    data object Album : Screen("album")
    data object ImageList : Screen("imageList/{albumName}") {
        fun createRoute(albumName: String) = "imageList/$albumName"
    }
}