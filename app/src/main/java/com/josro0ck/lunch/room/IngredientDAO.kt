package com.josro0ck.lunch.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.josro0ck.lunch.model.Ingredient

@Dao
interface IngredientDAO {

    @Query("SELECT * from ingredient_table ORDER BY name ASC")
    fun getAlphabetizedWords(): LiveData<List<Ingredient>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(word: Ingredient)

    @Query("DELETE FROM ingredient_table")
    suspend fun deleteAll()
}