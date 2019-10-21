package com.josro0ck.lunch

import com.josro0ck.lunch.enum.RatioScale
import com.josro0ck.lunch.model.Ingredient
import org.assertj.core.api.AbstractFloatAssert
import org.assertj.core.data.Offset
import java.util.*

fun AbstractFloatAssert<*>.isCloseTo(expected: Float) = this.isCloseTo(expected, Offset.offset(0.001f))

fun Date.equals(other: Date): Boolean {
    return this.toString().equals(other.toString())
}


//Never do this in production code
fun createTestIngredient(
    name: String = "mustard"
    , acquisitionDate: Date = Date(1)
    , averageExpirationDays: Int = 0
    , scale: RatioScale = RatioScale.KILOS
): Ingredient =
    Ingredient(name, acquisitionDate, averageExpirationDays, scale)
