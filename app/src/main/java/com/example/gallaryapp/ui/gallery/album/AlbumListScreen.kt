package com.example.gallaryapp.ui.gallery.album

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.gallaryapp.data.model.MediaAlbum
import com.example.gallaryapp.ui.components.GridImageItem
import com.example.gallaryapp.ui.components.ListImageItem
import com.example.gallaryapp.ui.components.MultiplePermissionsScreen
import com.example.gallaryapp.ui.navgraph.Screen

@Composable
fun AlbumListScreen(
    navController: NavController,
    viewModel: AlbumListViewModel = hiltViewModel<AlbumListViewModel>()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    AlbumListScreenContent(
        navController = navController, uiState = uiState.value,
        toggleViewType = viewModel::toggleViewType,
        fetchMedia = viewModel::fetchMedia
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AlbumListScreenContent(
    uiState: AlbumUiState,
    navController: NavController,
    toggleViewType: () -> Unit,
    fetchMedia: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Gallery") },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back or menu */ }) {
                        Icon(Icons.Default.Home, contentDescription = "Back")
                    }
                },
                actions = {
                    TextButton(
                        onClick = toggleViewType
                    ) {
                        Text("Toggle View")
                    }
                }
            )
        }
    ) { innerPadding ->
        // Screen content goes here
        MultiplePermissionsScreen(fetchMedia)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {

            when (uiState) {
                AlbumUiState.EMPTY -> {
                    Text("No folder found", modifier = Modifier.align(Alignment.Center))
                }

                AlbumUiState.LOADING -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(40.dp)
                            .align(Alignment.Center),
                        color = MaterialTheme.colorScheme.secondary,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant,
                    )
                }

                is AlbumUiState.SUCCESS -> {
                    when (uiState.viewType) {
                        ViewType.GRID_VIEW -> GridView(uiState.albumList, navController)
                        ViewType.LIST_VIEW -> ListView(uiState.albumList, navController)
                    }
                }
            }
        }
    }

}

@Composable
fun ListView(albumList: List<MediaAlbum>, navController: NavController) {
    LazyColumn(verticalArrangement = Arrangement.Top) {
        items(albumList) {
            AlbumCoverListItem(it, navController)
            Spacer(modifier = Modifier.size(5.dp))
        }
    }
}

@Composable
private fun AlbumCoverListItem(album: MediaAlbum, navController: NavController) {
    Box {
        ListImageItem(album.coverUri, modifier = Modifier.clickable {
            navController.navigate(Screen.ImageList.createRoute(album.albumName))
        })
        Text(
            text = "${album.albumName} (${album.count})",
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
                .background(Color.Black.copy(alpha = 0.5f))
                .padding(8.dp),
            fontSize = 12.sp
        )
    }
}

@Composable
private fun GridView(albumList: List<MediaAlbum>, navController: NavController) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 180.dp)
    ) {
        items(albumList) { album ->
            AlbumCoverGridItem(album, navController)
            Spacer(modifier = Modifier.size(5.dp))
        }
    }
}

@Composable
fun AlbumCoverGridItem(album: MediaAlbum, navController: NavController) {
    Box {
        GridImageItem(album.coverUri, modifier = Modifier.clickable {
            navController.navigate(Screen.ImageList.createRoute(album.albumName))
        })
        Text(
            text = "${album.albumName} (${album.count})",
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(start = 16.dp, end = 16.dp, bottom = 8.dp)
                .background(Color.Black.copy(alpha = 0.5f)),
            fontSize = 10.sp
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun AlbumListScreenPreview() {
//    AlbumListScreenContent(
//        navController = NavController(Loca)
//    )
}