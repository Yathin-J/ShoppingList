package com.example.shoppinglist.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shoppinglist.data.db.entity.ShoppingItem

@Dao
interface ShoppingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) // if there is already have same element it will replace it
    suspend fun upsert(item: ShoppingItem) : Long // upsert is the combination of insert and update
     /*
     * this will not run on the main thread it should on background thread asynchronously , so in kotlin we will
       coroutines for this we need to use suspend function
      */
    @Delete()
   suspend fun delete(item: ShoppingItem)

    @Query("SELECT * FROM shopping_item")
    fun getAllShoppingListItem(): LiveData<List<ShoppingItem>>
}