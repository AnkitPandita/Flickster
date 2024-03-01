package com.urbn.android.flickster.network.model

import com.google.gson.annotations.SerializedName

data class ApiResponseData(
    @SerializedName("Abstract")
    val abstract: String,
    @SerializedName("AbstractSource")
    val abstractSource: String,
    @SerializedName("AbstractText")
    val abstractText: String,
    @SerializedName("AbstractURL")
    val abstractURL: String,
    @SerializedName("Answer")
    val answer: String,
    @SerializedName("AnswerType")
    val answerType: String,
    @SerializedName("Definition")
    val definition: String,
    @SerializedName("DefinitionSource")
    val definitionSource: String,
    @SerializedName("DefinitionURL")
    val definitionURL: String,
    @SerializedName("Entity")
    val entity: String,
    @SerializedName("Heading")
    val heading: String,
    @SerializedName("Image")
    val image: String,
    @SerializedName("ImageHeight")
    val imageHeight: Int,
    @SerializedName("ImageIsLogo")
    val imageIsLogo: Int,
    @SerializedName("ImageWidth")
    val imageWidth: Int,
    @SerializedName("Infobox")
    val infobox: String,
    @SerializedName("Redirect")
    val redirect: String,
    @SerializedName("RelatedTopics")
    val relatedTopics: List<RelatedTopic>,
    @SerializedName("Results")
    val results: List<Any>,
    @SerializedName("Type")
    val type: String,
    @SerializedName("meta")
    val meta: Meta
)