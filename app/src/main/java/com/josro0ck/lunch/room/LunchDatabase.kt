package com.josro0ck.lunch.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.josro0ck.lunch.enum.RatioScale
import com.josro0ck.lunch.model.Ingredient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*

@Database(entities = arrayOf(Ingredient::class), version = 1)
@TypeConverters(Converters::class)
public abstract class LunchDatabase : RoomDatabase() {

    abstract fun ingredientDao(): IngredientDAO

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: LunchDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): LunchDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LunchDatabase::class.java,
                    "lunch_database"
                )
                    .addCallback(LunchDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }


    private class LunchDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        //only when DB is created
//        override fun onCreate(db: SupportSQLiteDatabase) {
//            super.onCreate(db)
//        }

        //every time DB is opened
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.ingredientDao())
                }
            }
        }

        suspend fun populateDatabase(ingredientDAO: IngredientDAO) {
            ingredientDAO.deleteAll()
            addSampleIngredients(ingredientDAO)
        }

        suspend private fun addSampleIngredients(ingredientDAO: IngredientDAO) {
            //Spices
            var i = Ingredient("Salt", Date(), -1, RatioScale.KILOS)
            ingredientDAO.insert(i)
            i = Ingredient("Pepper", Date(), -1, RatioScale.KILOS)
            ingredientDAO.insert(i)
            i = Ingredient("Garlic Salt", Date(), -1, RatioScale.KILOS)
            ingredientDAO.insert(i)
            i = Ingredient("Onion Powder", Date(), -1, RatioScale.KILOS)
            ingredientDAO.insert(i)
            i = Ingredient("Chicken Broth", Date(), -1, RatioScale.KILOS)
            ingredientDAO.insert(i)

            //Vegetables
            i = Ingredient("Onion", Date(), -1, RatioScale.KILOS)
            ingredientDAO.insert(i)
            i = Ingredient("Tomato", Date(), -1, RatioScale.KILOS)
            ingredientDAO.insert(i)
            i = Ingredient("Garlic", Date(), -1, RatioScale.KILOS)
            ingredientDAO.insert(i)
            i = Ingredient("Jalapeño", Date(), -1, RatioScale.KILOS)
            ingredientDAO.insert(i)
            i = Ingredient("Serrano Pepper", Date(), -1, RatioScale.KILOS)
            ingredientDAO.insert(i)
            i = Ingredient("Cilantro", Date(), -1, RatioScale.KILOS)
            ingredientDAO.insert(i)

            //Seeds
            i = Ingredient("Pinto Beans", Date(), -1, RatioScale.KILOS)
            ingredientDAO.insert(i)
            i = Ingredient("Short grain rice", Date(), -1, RatioScale.KILOS)
            ingredientDAO.insert(i)
            i = Ingredient("Long grain rice", Date(), -1, RatioScale.KILOS)
            ingredientDAO.insert(i)

            //Meat
            i = Ingredient("Bacon", Date(), -1, RatioScale.KILOS)
            ingredientDAO.insert(i)
            i = Ingredient("Smoked Salmon", Date(), -1, RatioScale.KILOS)
            ingredientDAO.insert(i)
            i = Ingredient("Chicharrón", Date(), -1, RatioScale.KILOS)
            ingredientDAO.insert(i)
            i = Ingredient("Egg", Date(), -1, RatioScale.KILOS)
            ingredientDAO.insert(i)

            //Dairy
            i = Ingredient("Evaporated Milk", Date(), -1, RatioScale.KILOS)
            ingredientDAO.insert(i)
            i = Ingredient("Condensed Milk", Date(), -1, RatioScale.KILOS)
            ingredientDAO.insert(i)
            i = Ingredient("Cream Cheese", Date(), -1, RatioScale.KILOS)
            ingredientDAO.insert(i)
            i = Ingredient("Salt", Date(), -1, RatioScale.KILOS)
            ingredientDAO.insert(i)
            i = Ingredient("Milk", Date(), -1, RatioScale.KILOS)
            ingredientDAO.insert(i)

            //Extracts
            i = Ingredient("Vanilla Extract", Date(), -1, RatioScale.KILOS)
            ingredientDAO.insert(i)

            //Powdered
            i = Ingredient("All purpose Flour", Date(), -1, RatioScale.KILOS)
            ingredientDAO.insert(i)
            i = Ingredient("Baking Powder", Date(), -1, RatioScale.KILOS)
            ingredientDAO.insert(i)
            i = Ingredient("Sugar", Date(), -1, RatioScale.KILOS)
            ingredientDAO.insert(i)
        }
    }
}