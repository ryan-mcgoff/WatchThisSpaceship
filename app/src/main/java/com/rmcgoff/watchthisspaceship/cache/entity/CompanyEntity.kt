package com.rmcgoff.watchthisspaceship.cache.entity

data class CompanyEntity(
    val companyName: String,
    val founderName: String,
    val year: String,
    val employees: Long,
    val launchSites: Long,
    val valuation: String
)