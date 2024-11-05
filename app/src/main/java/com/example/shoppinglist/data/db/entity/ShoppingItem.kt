package com.example.shoppinglist.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_item") // Specifies the table name in the database
data class ShoppingItem(
    @PrimaryKey(autoGenerate = true) // Defines the primary key for the table, which will auto-generate values
    var id: Int? = null, // Nullable integer for the primary key; it will be automatically generated

    @ColumnInfo(name = "item_name") // Specifies the column name in the database for this property
    var name: String, // The name of the shopping item

    @ColumnInfo(name = "item_amount") // Specifies the column name in the database for this property
    var amount: Int // The amount of the shopping item
)
{
    // Empty body; all properties are already defined
}
/*
* Package Declaration:

The class is part of the com.example.shoppinglist.data.db.entity package, indicating that it belongs to the data layer, specifically as an entity representation in the database.
Imports:

The necessary annotations from the Room library are imported. These annotations help define how the class interacts with the SQLite database.
@Entity Annotation:

kotlin
Copy code
@Entity(tableName = "shopping_item")
The @Entity annotation marks the class as a Room entity, which means it will correspond to a table in the SQLite database.
The tableName parameter specifies the name of the table, which is "shopping_item" in this case. Room conventions suggest using lowercase table names.
Data Class:

kotlin
Copy code
data class ShoppingItem(
The ShoppingItem class is defined as a data class. Data classes automatically generate useful methods such as toString(), equals(), and hashCode(), which are helpful for working with collections.
Primary Key:

kotlin
Copy code
@PrimaryKey(autoGenerate = true)
var id: Int? = null,
The @PrimaryKey annotation designates the id property as the primary key for the table.
The autoGenerate = true parameter means that the database will automatically generate a unique integer value for this field when a new record is inserted. This field is nullable (Int?), allowing it to be null before the item is inserted into the database.
Column Info:

kotlin
Copy code
@ColumnInfo(name = "item_name")
var name: String,
The @ColumnInfo annotation specifies the name of the column in the database that corresponds to the name property. In this case, it is "item_name".
The name property is of type String and holds the name of the shopping item.
Column Info for Amount:

kotlin
Copy code
@ColumnInfo(name = "item_amount")
var amount: Int
Similarly, this @ColumnInfo annotation specifies that the amount property will correspond to the "item_amount" column in the database.
The amount property is of type Int and represents the quantity or amount of the shopping item.
Summary
The ShoppingItem class serves as the data model for a shopping list item, mapping it to a corresponding table in the SQLite database using Room. It includes an auto-generated primary key (id), a name for the item (name), and the amount of the item (amount). This structure allows Room to perform CRUD operations on shopping list items effectively. The use of annotations helps define how this data class interacts with the database, ensuring that each property corresponds to a specific column in the shopping_item table.






*/