package com.example.gallaryapp.ui.gallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.gallaryapp.ui.navgraph.AppNavHost
import com.example.gallaryapp.ui.theme.GalleryAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GalleryAppTheme {
                val navController = rememberNavController()
                AppNavHost(navController)
            }
        }
    }
}
