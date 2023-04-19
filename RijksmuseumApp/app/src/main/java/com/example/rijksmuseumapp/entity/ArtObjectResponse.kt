package com.example.rijksmuseumapp.entity

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

class ArtObjectResponse(
    @SerializedName("artObjects") var artObjects: ArrayList<ArtObject>?,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readArrayList(ArtObject::class.java.classLoader) as? ArrayList<ArtObject>,
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeList(artObjects)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ArtObjectResponse> {
        override fun createFromParcel(parcel: Parcel): ArtObjectResponse {
            return ArtObjectResponse(parcel)
        }

        override fun newArray(size: Int): Array<ArtObjectResponse?> {
            return arrayOfNulls(size)
        }
    }
}