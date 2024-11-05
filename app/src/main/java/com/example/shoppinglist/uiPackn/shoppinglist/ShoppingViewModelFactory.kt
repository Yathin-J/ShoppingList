package com.example.shoppinglist.uiPackn.shoppinglist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shoppinglist.data.repository.ShoppingRepository

// Factory class to create instances of ShoppingListViewModel with a ShoppingRepository dependency
class ShoppingViewModelFactory(
    private val repository: ShoppingRepository // Injected repository instance to provide data access
) : ViewModelProvider.NewInstanceFactory() {

    // Overrides the create function to provide a customized way to instantiate ViewModels
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Returns an instance of ShoppingListViewModel, casting it as the generic ViewModel type T
        return ShoppingListViewModel(repository) as T
    }
}
/*
* ShoppingViewModelFactory class:

This class is a custom ViewModelProvider.Factory that provides a way to create instances of
 ShoppingListViewModel with specific dependencies, in this case, a ShoppingRepository.
ViewModelProvider.Factory allows us to create ViewModel instances with non-default constructors,
 which is useful when the ViewModel requires parameters like repository.
Constructor:

Takes a ShoppingRepository instance as a parameter. This repository will be passed to the ShoppingListViewModel
 so it can access the necessary data operations.
create function:

Overrides the create method from ViewModelProvider.NewInstanceFactory.
create checks the type of the ViewModel being requested and provides an instance of ShoppingListViewModel,
passing in the repository dependency.
The returned instance is cast to the generic type T,
 which extends ViewModel, making it compatible with the ViewModelProvider framework.
*/




