package com.example.my_vk.models

import android.os.Parcel
import android.os.Parcelable
import org.json.JSONObject

class FriendsModel(
    val id: Int = 0,
    val firstName: String = "",
    val lastName: String = "",
    val photo: String = "",
    val isOnline:Int) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(firstName)
        parcel.writeString(lastName)
        parcel.writeString(photo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FriendsModel> {
        override fun createFromParcel(parcel: Parcel): FriendsModel {
            return FriendsModel(parcel)
        }

        override fun newArray(size: Int): Array<FriendsModel?> {
            return arrayOfNulls(size)
        }

        fun parse(json: JSONObject): FriendsModel
            = FriendsModel(
                id = json.optInt("id", 0),
                firstName = json.optString("first_name", ""),
                lastName = json.optString("last_name", ""),
                photo = json.optString("photo_100", ""),
                isOnline = json.optInt("online", 0)
            )
    }
}