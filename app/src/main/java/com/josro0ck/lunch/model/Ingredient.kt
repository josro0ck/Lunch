package com.josro0ck.lunch.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.josro0ck.lunch.enum.RatioScale
import java.util.Date

@Entity(tableName = "ingredient_table")
data class Ingredient(
    @PrimaryKey @ColumnInfo var name: String,
    @ColumnInfo var acquisitionDate: Date,
    @ColumnInfo var averageExpirationDays: Int,
    @ColumnInfo var amount: Float,
    @ColumnInfo var scale: RatioScale
) {
    constructor() : this("", Date(), 0, 0f, RatioScale.QUANTITY)
}