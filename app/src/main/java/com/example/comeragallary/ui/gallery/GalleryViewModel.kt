package com.example.comeragallary.ui.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comeragallary.data.model.MediaAlbum
import com.example.comeragallary.domain.usecases.AlbumListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val albumUseCase: AlbumListUseCase
) : ViewModel() {
    private var albumList: List<MediaAlbum> = emptyList()
    fun fetchMedia() {
        if (albumList.isEmpty()) {
            viewModelScope.launch {
                albumUseCase.invoke().collect {
                    if (it.isSuccess) {
                        albumList = it.getOrDefault(emptyList())
                    }
                }

            }
        }
    }

}