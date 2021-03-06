package com.rmcgoff.watchthisspaceship.network.model.launch

import com.google.gson.annotations.SerializedName

data class Failure(
    @SerializedName("time") val time: Long,
    @SerializedName("altitude") val altitude: Long,
    @SerializedName("reason") val reason: String,
)