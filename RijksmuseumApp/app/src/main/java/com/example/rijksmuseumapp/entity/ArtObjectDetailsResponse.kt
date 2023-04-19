package com.example.rijksmuseumapp.entity

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class ArtObjectDetailResponse private constructor(`in`: Parcel) : Parcelable {
    @SerializedName("artObject")
    private val mArtObject: ArtObjectDetails?

    val artObject: ArtObjectDetails?
        get() = mArtObject

    override fun writeToParcel(out: Parcel, flags: Int) {
        out.writeValue(mArtObject)
    }

    override fun describeContents(): Int {
        return 0
    }

    init {
        mArtObject =
            `in`.readValue(ArtObjectDetails::class.java.classLoader) as ArtObjectDetails?
    }

    companion object CREATOR : Parcelable.Creator<ArtObjectDetailResponse> {
        override fun createFromParcel(parcel: Parcel): ArtObjectDetailResponse {
            return ArtObjectDetailResponse(parcel)
        }

        override fun newArray(size: Int): Array<ArtObjectDetailResponse?> {
            return arrayOfNulls(size)
        }
    }
}