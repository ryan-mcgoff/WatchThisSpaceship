package com.rmcgoff.watchthisspaceship.network.model.launch

import com.google.gson.annotations.SerializedName
import com.rmcgoff.watchthisspaceship.network.model.common.Links

data class LaunchNetwork(
    @SerializedName("links") val links: Links,
    @SerializedName("static_fire_date_utc") val static_fire_date_utc: String,
    @SerializedName("static_fire_date_unix") val static_fire_date_unix: Long,
    @SerializedName("tdb") val tdb: Boolean,
    @SerializedName("net") val net: Boolean,
    @SerializedName("window") val window: Long,
    @SerializedName("rocket") val rocket: String,
    @SerializedName("success") val success: Boolean,
    @SerializedName("failures") val failures: List<Failure>,
    @SerializedName("details") val details: String,
    @SerializedName("crew") val crew: List<String>,
    @SerializedName("ships") val ships: List<String>,
    @SerializedName("capsules") val capsules: List<String>,
    @SerializedName("payloads") val payloads: List<String>,
    @SerializedName("launchpad") val launchpad: String,
    @SerializedName("auto_update") val auto_update: Boolean,
    @SerializedName("flight_number") val flight_number: Long,
    @SerializedName("name") val name: String,
    @SerializedName("date_utc") val date_utc: String,
    @SerializedName("date_unix") val date_unix: Long,
    @SerializedName("date_local") val date_local: String,
    @SerializedName("date_precision") val date_precision: String,
    @SerializedName("upcoming") val upcoming: Boolean,
    @SerializedName("cores") val cores: List<Cores>,
    @SerializedName("id") val id: String
)