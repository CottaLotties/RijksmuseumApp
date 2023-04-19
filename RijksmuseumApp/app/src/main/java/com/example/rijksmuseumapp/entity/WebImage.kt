package com.example.rijksmuseumapp.entity

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class WebImage (
    @SerializedName("guid") val guid: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("width") val width: Int?,
    @SerializedName("height") val height: Int?,
    @SerializedName("offsetPercentageX") val offsetPercentageX: Int?,
    @SerializedName("offsetPercentageY") val offsetPercentageY: Int?
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}