package com.rmcgoff.watchthisspaceship.domain.mapper

import com.rmcgoff.watchthisspaceship.domain.model.Company
import com.rmcgoff.watchthisspaceship.network.model.company.CompanyNetwork
import java.text.NumberFormat
import javax.inject.Inject

/**
 * Maps from Company network DTO to CompanyEntity
 */
class CompanyEntityMapper @Inject constructor() : Mapper<CompanyNetwork, Company> {
    override suspend fun map(from: CompanyNetwork): Company {
        return Company(
            companyName = from.name,
            founderName = from.founder,
            year = "",
            employees = from.employees,
            launchSites = from.launch_sites,
            valuation = convertCurrencyToString(from.valuation)
        )
    }

    override suspend fun mapList(fromList: List<CompanyNetwork>): List<Company> {
        return fromList.map { launch ->
            map(launch)
        }
    }

    private fun convertCurrencyToString(currency: Long): String {
        val currencyFormatter = NumberFormat.getCurrencyInstance()
        return currencyFormatter.format(currency)
    }
}