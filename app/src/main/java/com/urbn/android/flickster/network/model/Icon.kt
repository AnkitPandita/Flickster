package com.urbn.android.flickster.network.model

import com.google.gson.annotations.SerializedName

data class Icon(
    @SerializedName("Height")
    val height: String,
    @SerializedName("URL")
    val url: String,
    @SerializedName("Width")
    val width: String
)