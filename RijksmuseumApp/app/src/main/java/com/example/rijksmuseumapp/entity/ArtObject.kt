package com.example.rijksmuseumapp.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "art_objects")
data class ArtObject(
    @PrimaryKey
    @ColumnInfo(name = "object_number") val objectNumber: String,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "first_maker") val principalOrFirstMaker: String?,
    @Embedded
    val webImage: WebImage?
)