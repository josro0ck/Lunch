package com.josro0ck.lunch.repository

import com.josro0ck.lunch.enum.RatioScale
import com.josro0ck.lunch.extensions.addDays
import com.josro0ck.lunch.model.Ingredient
import java.util.*

class Storage {

    private val storage = mutableListOf<Ingredient>()

    fun theThingToBeAccessedPublicly(): List<Ingredient> = storage

    init {

        val d = Date()
        val hundredDaysBack = d.addDays(-100)
        val fourDaysBack = d.addDays(-4)
        val fiveDaysBack = d.addDays(-5)

        storage.add(
            Ingredient(
                "Jalapeño Pepper"
                , fourDaysBack
                , 3
                , 2f
                , RatioScale.LBS
            )
        )
        storage.add(
            Ingredient(
                "Vegetable Oil"
                , fiveDaysBack
                , 60
                , 2f
                , RatioScale.LBS
            )
        )
        storage.add(
            Ingredient(
                "Salt"
                , hundredDaysBack
                , -1
                , 2f
                , RatioScale.LBS
            )
        )


    }
}