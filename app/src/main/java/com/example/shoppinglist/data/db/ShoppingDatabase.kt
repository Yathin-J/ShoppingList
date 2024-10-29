package com.example.shoppinglist.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shoppinglist.data.db.entity.ShoppingItem // Import your entity
import com.example.shoppinglist.data.db.ShoppingDao // Import your DAO interface

@Database(
    entities = [ShoppingItem::class], // Reference the correct entity class
    version = 1,
)
abstract class ShoppingDatabase : RoomDatabase() {

    abstract fun getShoppingDao(): ShoppingDao // Correct naming for DAO

    companion object {
        @Volatile
        private var instance: ShoppingDatabase? = null
        private val LOCK = Any()



        private fun createDatabase(context: Context): ShoppingDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                ShoppingDatabase::class.java,
                "shopping.db"
            ).build()
        }

        fun getInstance(context: Context): ShoppingDatabase {
            return instance ?: synchronized(LOCK) {
                instance ?: createDatabase(context).also { instance = it }
            }
        }
    }
}
