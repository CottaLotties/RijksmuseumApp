package com.example.rijksmuseumapp.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "art_object_details")
data class ArtObjectDetails(
    @PrimaryKey
    @ColumnInfo(name = "object_number") val objectNumber: String,
    @ColumnInfo(name = "title") val title: String?,
    @Embedded
    val webImage: WebImage?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "principal") val principalOrFirstMaker: String?,
)