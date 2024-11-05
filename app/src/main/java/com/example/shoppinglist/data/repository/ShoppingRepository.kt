package com.example.shoppinglist.data.repository

import com.example.shoppinglist.data.db.ShoppingDatabase
import com.example.shoppinglist.data.db.entity.ShoppingItem

// Repository class for managing ShoppingItem data operations
class ShoppingRepository(
    private val db: ShoppingDatabase // Instance of ShoppingDatabase to access DAO
) {
    // Upsert operation: Insert or update a ShoppingItem in the database
    suspend fun upsert(item: ShoppingItem) = db.getShoppingDao().upsert(item)

    // Delete operation: Remove a ShoppingItem from the database
    suspend fun delete(item: ShoppingItem) = db.getShoppingDao().delete(item)

    // Retrieve all ShoppingItem entities from the database
    fun getAllShoppingItem() = db.getShoppingDao().getAllShoppingListItem()
}
/*
* Class Definition:

The ShoppingRepository class is defined to handle operations related to ShoppingItem entities.
It takes an instance of ShoppingDatabase as a constructor parameter, allowing it to access the database and its Data Access Object (DAO).
Constructor:

private val db: ShoppingDatabase: A property that holds the reference to the ShoppingDatabase. This enables the repository to call the appropriate methods on the DAO to interact with the database.
suspend fun upsert(item: ShoppingItem):

This function is responsible for either inserting a new ShoppingItem into the database or updating an existing item (hence the term "upsert").
It is a suspend function, meaning it must be called from a coroutine or another suspend function. This ensures that the database operations run off the main thread, preventing UI freezes.
Calls the upsert method of the DAO to perform the operation.
suspend fun delete(item: ShoppingItem):

This function deletes a specified ShoppingItem from the database.
Similar to upsert, it is also a suspend function to ensure it runs on a background thread.
fun getAllShoppingItem():

This function retrieves all ShoppingItem entities from the database.
It returns a LiveData or Flow object (depending on your DAO implementation), which allows the UI to observe changes in the data. This means that any updates to the shopping list will automatically be reflected in the UI when it observes this data.*/