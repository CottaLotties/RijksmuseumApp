package com.example.rijksmuseumapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rijksmuseumapp.entity.ArtObject
import com.example.rijksmuseumapp.ArtObjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ArtObjectViewModel @Inject constructor(private val artObjectRepository: ArtObjectRepository) : ViewModel() {

    private val currentPage = 1
    private val pageSize = 100
    private var isLoading = false
    private var isLastPage = false

    fun fetchDataFromRepository(onDataFetched: (List<ArtObject>) -> Unit, onError: (Throwable) -> Unit) {
        if (isLoading || isLastPage) return

        isLoading = true
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val artObjects = artObjectRepository.getArtObjects(currentPage, pageSize)
                    if (artObjects.isEmpty()) {
                        isLastPage = true
                    } else {
                        onDataFetched(artObjects)
                    }
                } catch (e: Exception) {
                    onError(e)
                }
            }
            isLoading = false
        }
    }
}