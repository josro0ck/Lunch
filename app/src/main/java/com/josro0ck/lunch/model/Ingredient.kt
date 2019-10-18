package com.josro0ck.lunch.model

import com.josro0ck.lunch.enum.RatioScale
import java.util.Date

data class Ingredient(
    val name: String,
    val acquisitionDate: Date,
    val averageExpirationDays: Int,
    var amount: Float,
    val scale: RatioScale
)