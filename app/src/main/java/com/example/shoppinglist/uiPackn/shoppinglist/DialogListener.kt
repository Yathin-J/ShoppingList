package com.example.shoppinglist.uiPackn.shoppinglist

import com.example.shoppinglist.data.db.entity.ShoppingItem

// Interface for handling dialog interactions
interface DialogListener {

    // Function called when the "Add" button in the dialog is clicked
    fun onAddButtonClicked(item: ShoppingItem)
}
