package com.vkasurinen.qt_project.data.mappers


import com.vkasurinen.qt_project.data.remote.BreweryDto
import com.vkasurinen.qt_project.domain.BreweryModel

fun BreweryDto.toBreweryModel(): BreweryModel {
    return BreweryModel(
        id = id,
        name = name,
        breweryType = brewery_type,
        address1 = address_1,
        address2 = address_2,
        address3 = address_3,
        city = city,
        stateProvince = state_province,
        postalCode = postal_code,
        country = country,
        longitude = longitude?.toDoubleOrNull(),
        latitude = latitude?.toDoubleOrNull(),
        phone = phone,
        websiteUrl = website_url,
        state = state,
        street = street
    )
}


