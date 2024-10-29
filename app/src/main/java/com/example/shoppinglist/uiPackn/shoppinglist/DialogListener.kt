package com.example.shoppinglist.uiPackn.shoppinglist

import com.example.shoppinglist.data.db.entity.ShoppingItem

interface DialogListener {

    fun onAddButtonClicked(item:ShoppingItem)
}