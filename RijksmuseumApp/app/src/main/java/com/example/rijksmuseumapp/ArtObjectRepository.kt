package com.example.rijksmuseumapp

import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.rijksmuseumapp.Constants.API_KEY
import com.example.rijksmuseumapp.Constants.FORMAT
import com.example.rijksmuseumapp.database.AppDatabase
import com.example.rijksmuseumapp.database.ArtObjectDao
import com.example.rijksmuseumapp.entity.ArtObject
import com.example.rijksmuseumapp.entity.ArtObjectDetails
import com.example.rijksmuseumapp.network.ArtObjectService
import javax.inject.Inject

class ArtObjectRepository @Inject constructor(
    private val dao: ArtObjectDao,
    private val service: ArtObjectService,
    private val database: AppDatabase
) {

    suspend fun getArtObjects(currentPage: Int, pageSize: Int): List<ArtObject> {
        val cachedArtObjects = dao.getArtObjects()
        if (cachedArtObjects.isNotEmpty()) {
            return cachedArtObjects
        } else {
            val hasData =
                database.query(SimpleSQLiteQuery("SELECT EXISTS (SELECT * FROM art_objects LIMIT 1)"))
                    ?.moveToFirst()
                    ?: false
            if (hasData) {
                val response =
                    service.getArtObjects(API_KEY, FORMAT, pageSize, true, "artist", currentPage)
                        ?.execute()
                if (response?.isSuccessful == true) {
                    val artObjects = response?.body()?.artObjects ?: emptyList()
                    if (artObjects.isNotEmpty()) {
                        dao.insertArtObjects(artObjects)
                    }
                    return artObjects
                } else {
                    return emptyList()
                }
            } else {
                return emptyList()
            }
        }
    }

    suspend fun getArtObjectDetails(objectNumber: String): ArtObjectDetails? {
        val cachedArtObjectDetails = dao.getArtObjectDetails(objectNumber)
        if (cachedArtObjectDetails != null) {
            return cachedArtObjectDetails
        } else {
            val response = service.getArtObjectDetails(objectNumber, API_KEY, FORMAT)?.execute()
            if (response?.isSuccessful == true) {
                val artObjectDetails = response.body()?.artObject
                artObjectDetails?.let { dao.addArtProjectDetails(it) }
                return artObjectDetails
            } else {
                return null
            }
        }
    }
}