package com.example.gallaryapp.ui.gallery.imagelist

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.gallaryapp.ui.components.GridImageItem
import com.example.gallaryapp.ui.components.ListImageItem
import com.example.gallaryapp.ui.gallery.album.ViewType
import com.example.gallaryapp.ui.gallery.image.FullScreenImage

@Composable
fun ImageListScreen(
    albumName: String?,
    onBackPressed: () -> Unit,
    viewModel: ImageListViewModel = hiltViewModel<ImageListViewModel>(),
) {
    LaunchedEffect("key1") {
        albumName?.let {
            viewModel.fetchAlbumImages(albumName)
        }
    }
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    ImageListContent(
        albumName = albumName,
        uiState = uiState.value,
        onBackPressed = onBackPressed,
        showFullImage = viewModel::showFullImage,
        hideFullImage = viewModel::hideFullImage,
        toggleViewType = viewModel::toggleViewType
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ImageListContent(
    albumName: String?,
    uiState: ImageListUiState,
    onBackPressed: () -> Unit,
    showFullImage: (Uri) -> Unit,
    hideFullImage: () -> Unit,
    toggleViewType: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(albumName ?: "Gallery") },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
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
        Box(modifier = Modifier.padding(innerPadding)) {
            when (uiState) {
                ImageListUiState.LOADING -> {
                    CircularProgressIndicator(
                        modifier = Modifier.size(120.dp),
                        color = MaterialTheme.colorScheme.secondary,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant,
                    )
                }

                is ImageListUiState.SUCCESS -> {
                    when (uiState.viewType) {
                        ViewType.GRID_VIEW -> GridView(
                            uiState.album.mediaUris, showFullImage
                        )

                        ViewType.LIST_VIEW -> ListView(uiState.album.mediaUris, showFullImage)
                    }
                    uiState.imageUri?.let {
                        FullScreenImage(it) {
                            hideFullImage()
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun GridView(imageList: List<Uri>, showFullImage: (Uri) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp)
    ) {
        items(imageList) { image ->
            GridImageItem(image, Modifier.clickable {
                showFullImage(image)
            })
        }
    }
}

@Composable
private fun ListView(imageList: List<Uri>, showFullImage: (Uri) -> Unit) {
    LazyColumn {
        items(imageList) { image ->
            ListImageItem(image, Modifier.clickable {
                showFullImage(image)
            })
        }
    }
}
