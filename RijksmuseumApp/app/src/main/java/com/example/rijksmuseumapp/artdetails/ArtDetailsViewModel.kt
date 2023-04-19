package com.example.rijksmuseumapp.artdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rijksmuseumapp.entity.ArtObjectDetails
import com.example.rijksmuseumapp.ArtObjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ArtDetailsViewModel @Inject constructor(private val repository: ArtObjectRepository) : ViewModel() {

    private val _artObjectDetails = MutableLiveData<ArtObjectDetails>()
    val artObjectDetails: LiveData<ArtObjectDetails> get() = _artObjectDetails

    fun getArtObjectDetails(objectNumber: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val details = repository.getArtObjectDetails(objectNumber)
            withContext(Dispatchers.Main) {
                _artObjectDetails.value = details
            }
        }
    }
}
