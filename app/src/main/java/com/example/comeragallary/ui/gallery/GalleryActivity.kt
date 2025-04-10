package com.example.comeragallary.ui.gallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.comeragallary.ui.navgraph.AppNavHost
import com.example.comeragallary.ui.theme.ComeraGallaryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComeraGallaryTheme {
                val navController = rememberNavController()
                AppNavHost(navController)
            }
        }
    }
}
