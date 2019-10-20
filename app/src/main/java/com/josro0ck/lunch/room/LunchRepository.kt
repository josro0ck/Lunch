package com.josro0ck.lunch.room

import androidx.lifecycle.LiveData
import com.josro0ck.lunch.model.Ingredient

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class LunchRepository(private val ingredientDao: IngredientDAO) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allWords: LiveData<List<Ingredient>> = ingredientDao.getAlphabetizedWords()

    suspend fun insert(ingredient: Ingredient) {
        ingredientDao.insert(ingredient)
    }
}