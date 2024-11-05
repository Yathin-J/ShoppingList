package com.example.shoppinglist.uiPackn.shoppinglist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.data.db.entity.ShoppingItem
import com.example.shoppinglist.data.repository.ShoppingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// ViewModel class for managing UI-related data in a lifecycle-conscious way
class ShoppingListViewModel(
    private val repository: ShoppingRepository // Injected repository instance to interact with the database
) : ViewModel() {

    // Function to insert or update a shopping item in the database
    fun upsert(item: ShoppingItem) = viewModelScope.launch((Dispatchers.Main)) {
        repository.upsert(item) // Calls the repository's upsert function to add or update the item
    }

    // Function to delete a shopping item from the database
    fun delete(item: ShoppingItem) = viewModelScope.launch((Dispatchers.Main)) {
        repository.delete(item) // Calls the repository's delete function to remove the item
    }

    // Function to retrieve all shopping items from the database
    // Note: This is a synchronous call; typically, it returns a LiveData or Flow
    fun getAllShoppingListItem() = repository.getAllShoppingItem()
}
