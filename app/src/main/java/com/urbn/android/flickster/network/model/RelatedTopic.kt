package com.urbn.android.flickster.network.model

import com.google.gson.annotations.SerializedName

data class RelatedTopic(
    @SerializedName("FirstURL")
    val firstUrl: String,
    @SerializedName("Icon")
    val icon: Icon,
    @SerializedName("Result")
    val result: String,
    @SerializedName("Text")
    val text: String
)