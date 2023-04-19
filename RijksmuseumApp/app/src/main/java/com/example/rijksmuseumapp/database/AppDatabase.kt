package com.example.rijksmuseumapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rijksmuseumapp.Constants.APP_DATABASE_VERSION
import com.example.rijksmuseumapp.entity.ArtObject
import com.example.rijksmuseumapp.entity.ArtObjectDetails

@Database(
    entities = [ArtObject::class, ArtObjectDetails::class], version = APP_DATABASE_VERSION
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun artObjectDao(): ArtObjectDao
}
