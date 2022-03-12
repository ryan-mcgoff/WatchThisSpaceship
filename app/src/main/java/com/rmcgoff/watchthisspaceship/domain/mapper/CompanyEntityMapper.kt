package com.rmcgoff.watchthisspaceship.domain.mapper

import com.rmcgoff.watchthisspaceship.cache.entity.CompanyEntity
import com.rmcgoff.watchthisspaceship.network.model.company.Company
import java.text.NumberFormat
import javax.inject.Inject

/**
 * Maps from Company network DTO to CompanyEntity
 */
class CompanyEntityMapper @Inject constructor() : Mapper<Company, CompanyEntity> {
    override suspend fun map(from: Company): CompanyEntity {
        return CompanyEntity(
            companyName = from.name,
            founderName = from.founder,
            year = "",
            employees = from.employees,
            launchSites = from.launch_sites,
            valuation = convertCurrencyToString(from.valuation)
        )
    }

    override suspend fun mapList(fromList: List<Company>): List<CompanyEntity> {
        return fromList.map { launch ->
            map(launch)
        }
    }

    fun convertCurrencyToString(currency: Long): String {
        val currencyFormatter = NumberFormat.getCurrencyInstance()
        currencyFormatter.maximumIntegerDigits = 2
        return currencyFormatter.format(currency)
    }
}