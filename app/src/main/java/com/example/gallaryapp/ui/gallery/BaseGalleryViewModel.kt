package com.example.gallaryapp.ui.gallery

import androidx.lifecycle.ViewModel
import com.example.gallaryapp.ui.gallery.album.ViewType

open class BaseGalleryViewModel : ViewModel() {

    var viewType = ViewType.LIST_VIEW

    open fun toggleViewType() {
        viewType = if(viewType == ViewType.LIST_VIEW) ViewType.GRID_VIEW else ViewType.LIST_VIEW
    }
}