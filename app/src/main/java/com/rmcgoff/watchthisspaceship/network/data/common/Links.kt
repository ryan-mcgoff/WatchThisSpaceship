package com.rmcgoff.watchthisspaceship.network.data.common

import com.google.gson.annotations.SerializedName
import com.rmcgoff.watchthisspaceship.network.data.launch.Flickr
import com.rmcgoff.watchthisspaceship.network.data.launch.Patch
import com.rmcgoff.watchthisspaceship.network.data.launch.Reddit

data class Links(
    @SerializedName("patch") val patch: Patch,
    @SerializedName("reddit") val reddit: Reddit,
    @SerializedName("flickr") val flickr: Flickr,
    @SerializedName("presskit") val presskit: String,
    @SerializedName("webcast") val webcast: String,
    @SerializedName("youtube_id") val youtube_id: String,
    @SerializedName("article") val article: String,
    @SerializedName("wikipedia") val wikipedia: String
)