package com.rmcgoff.watchthisspaceship.domain.model

data class Company(
    val companyName: String,
    val founderName: String,
    val year: String,
    val employees: Long,
    val launchSites: Long,
    val valuation: String
)