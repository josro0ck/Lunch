package com.josro0ck.lunch.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.josro0ck.lunch.model.Ingredient

@Database(entities = arrayOf(Ingredient::class), version = 1)
@TypeConverters(Converters::class)
public abstract class LunchDatabase : RoomDatabase() {

    abstract fun ingredientDao(): IngredientDAO

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: LunchDatabase? = null

        fun getDatabase(context: Context): LunchDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LunchDatabase::class.java,
                    "lunch_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}