package com.example.rijksmuseumapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rijksmuseumapp.entity.ArtObject
import com.example.rijksmuseumapp.entity.ArtObjectDetails

@Dao
interface ArtObjectDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArtObjects(artObject: List<ArtObject>)

    @Query("SELECT * FROM art_objects")
    fun getArtObjects(): List<ArtObject>

    @Query("SELECT * FROM art_object_details WHERE [object_number] = :key")
    fun getArtObjectDetails(key: String): ArtObjectDetails

    @Insert(onConflict = OnConflictStrategy.REPLACE, entity = ArtObjectDetails::class)
    fun addArtProjectDetails(artObjectDetails: ArtObjectDetails)
}
