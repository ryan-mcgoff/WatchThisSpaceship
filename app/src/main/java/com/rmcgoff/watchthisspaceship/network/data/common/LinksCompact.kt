package com.rmcgoff.watchthisspaceship.network.data.common

import com.google.gson.annotations.SerializedName

data class LinksCompact(
    @SerializedName("website") val website: String,
    @SerializedName("flickr") val flickr: String,
    @SerializedName("twitter") val twitter: String,
    @SerializedName("elon_twitter") val elon_twitter: String
)