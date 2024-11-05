package com.example.shoppinglist.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shoppinglist.data.db.entity.ShoppingItem

@Dao // Annotation to indicate that this interface is a Data Access Object
interface ShoppingDao {

    // Method to insert or update a ShoppingItem in the database
    @Insert(onConflict = OnConflictStrategy.REPLACE) // If there's a conflict (duplicate), replace the existing entry
    suspend fun upsert(item: ShoppingItem): Long // Upsert combines insert and update operations

    /*
     * This function is a suspend function, meaning it should not run on the main thread.
     * It must be called from a coroutine or another suspend function to perform the database operation asynchronously.
     */

    @Delete // Annotation to indicate this method will delete an item
    suspend fun delete(item: ShoppingItem) // Deletes the specified ShoppingItem from the database

    // Query to fetch all shopping items from the database
    @Query("SELECT * FROM shopping_item") // SQL query to select all items from the shopping_item table
    fun getAllShoppingListItem(): LiveData<List<ShoppingItem>> // Returns LiveData containing a list of ShoppingItem objects
}
/*
* Package Declaration:

The ShoppingDao interface is part of the com.example.shoppinglist.data.db package, indicating its role in the database layer of the application.
Imports:

Imports the necessary classes, including LiveData from the Android Architecture Components and various annotations from the Room library, along with the ShoppingItem entity.
@Dao Annotation:

The @Dao annotation marks the interface as a Data Access Object, which Room uses to generate the necessary code for database operations.
Upsert Method:

kotlin
Copy code
@Insert(onConflict = OnConflictStrategy.REPLACE)
suspend fun upsert(item: ShoppingItem): Long
This method is responsible for inserting a ShoppingItem into the database or updating it if it already exists (based on its primary key).
The onConflict = OnConflictStrategy.REPLACE parameter specifies that if there's a conflict (e.g., an item with the same ID already exists), Room will replace the existing entry with the new one.
The method is marked as suspend, which means it should be called from a coroutine to avoid blocking the main thread. The return type Long indicates that the method will return the row ID of the newly inserted or updated item.
Delete Method:

kotlin
Copy code
@Delete
suspend fun delete(item: ShoppingItem)
This method deletes a specified ShoppingItem from the database.
It is also marked as suspend, ensuring that it runs in a background thread and does not block the main UI thread.
Get All Shopping Items Method:

kotlin
Copy code
@Query("SELECT * FROM shopping_item")
fun getAllShoppingListItem(): LiveData<List<ShoppingItem>>
This method fetches all ShoppingItem entries from the shopping_item table in the database.
It uses the @Query annotation to specify a SQL query.
The return type is LiveData<List<ShoppingItem>>, which allows the UI to observe the list of shopping items. When the database changes (e.g., items are added or deleted), the LiveData object will notify its observers, allowing the UI to update automatically.*/