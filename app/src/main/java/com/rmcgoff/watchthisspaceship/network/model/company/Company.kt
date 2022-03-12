package com.rmcgoff.watchthisspaceship.network.model.company

import com.google.gson.annotations.SerializedName
import com.rmcgoff.watchthisspaceship.network.model.common.LinksCompact

data class Company(
    @SerializedName("headquarters") val headquarters: Headquarters,
    @SerializedName("links") val links: LinksCompact,
    @SerializedName("name") val name: String,
    @SerializedName("founder") val founder: String,
    @SerializedName("founded") val founded: Long,
    @SerializedName("employees") val employees: Long,
    @SerializedName("vehicles") val vehicles: Long,
    @SerializedName("launch_sites") val launch_sites: Long,
    @SerializedName("test_sites") val test_sites: Long,
    @SerializedName("ceo") val ceo: String,
    @SerializedName("cto") val cto: String,
    @SerializedName("coo") val coo: String,
    @SerializedName("cto_propulsion") val cto_propulsion: String,
    @SerializedName("valuation") val valuation: Long,
    @SerializedName("summary") val summary: String,
    @SerializedName("id") val id: String
)