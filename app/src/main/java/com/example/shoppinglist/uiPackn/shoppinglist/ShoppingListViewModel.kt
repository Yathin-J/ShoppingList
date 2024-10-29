package com.example.shoppinglist.uiPackn.shoppinglist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppinglist.data.db.entity.ShoppingItem
import com.example.shoppinglist.data.repository.ShoppingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingListViewModel(
    private  val repository: ShoppingRepository
):ViewModel() {

    fun upsert(item: ShoppingItem) = viewModelScope.launch((Dispatchers.Main)) {
        repository.upsert(item)
    }
    fun delete(item: ShoppingItem) = viewModelScope.launch((Dispatchers.Main)) {
        repository.delete(item)
    }
    fun  getAllShoppingListItem() = repository.getAllShoppingItem()
}