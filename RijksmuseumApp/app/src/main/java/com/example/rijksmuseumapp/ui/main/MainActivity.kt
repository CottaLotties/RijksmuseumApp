package com.example.rijksmuseumapp.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rijksmuseumapp.R
import com.example.rijksmuseumapp.database.AppDatabase
import com.example.rijksmuseumapp.database.ArtObjectDao
import com.example.rijksmuseumapp.network.ArtObjectService
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}