package com.room.omdb


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Rating(
    @SerializedName("Source")
    val source: String?,
    @SerializedName("Value")
    val value: String?
):Serializable