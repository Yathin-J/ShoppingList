package com.example.shoppinglist.uiPackn.shoppinglist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shoppinglist.data.repository.ShoppingRepository

class ShoppingViewModelFactory(
    private  val repository : ShoppingRepository

) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ShoppingListViewModel(repository) as T
    }

}