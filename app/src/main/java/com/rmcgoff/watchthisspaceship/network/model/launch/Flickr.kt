package com.rmcgoff.watchthisspaceship.network.model.launch

import com.google.gson.annotations.SerializedName

data class Flickr(
    @SerializedName("small") val small: List<String>,
    @SerializedName("original") val original: List<String>
)