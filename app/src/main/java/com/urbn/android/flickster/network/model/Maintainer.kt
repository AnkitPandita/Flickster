package com.urbn.android.flickster.network.model

import com.google.gson.annotations.SerializedName

data class Maintainer(
    @SerializedName("github")
    val github: String
)