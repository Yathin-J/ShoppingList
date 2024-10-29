package com.example.shoppinglist.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_item") // table name should be in lowercase
data class ShoppingItem(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "item_name")
    var name: String,

    @ColumnInfo(name = "item_amount")
    var amount: Int
)
{

}
