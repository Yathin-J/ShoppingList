package com.example.shoppinglist.uiPackn.shoppinglist

import ShoppingListAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglist.data.db.ShoppingDatabase
import com.example.shoppinglist.data.db.entity.ShoppingItem
import com.example.shoppinglist.data.repository.ShoppingRepository
import com.example.shoppinglist.databinding.ActivityShoppingBinding

// Main activity for displaying the shopping list
class ShoppingListActivity : AppCompatActivity() {

    // View binding for accessing views in activity_shopping.xml
    private lateinit var binding: ActivityShoppingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the binding and set it as the content view
        binding = ActivityShoppingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the toolbar as the action bar for this activity
        setSupportActionBar(binding.toolbar)

        // Initialize Database, Repository, ViewModelFactory, and ViewModel
        val database = ShoppingDatabase.getInstance(this) // Retrieves a singleton instance of the database
        val repository = ShoppingRepository(database) // Creates a repository instance using the database
        val factory = ShoppingViewModelFactory(repository) // Creates a ViewModel factory with the repository
        val viewModel = ViewModelProvider(this, factory).get(ShoppingListViewModel::class.java) // Gets ViewModel instance

        // Initialize the RecyclerView adapter and set it to the RecyclerView
        val adapter = ShoppingListAdapter(listOf(), viewModel) // Adapter for displaying list items
        binding.rvShoppingList.layoutManager = LinearLayoutManager(this) // Sets a linear layout for RecyclerView
        binding.rvShoppingList.adapter = adapter // Binds the adapter to the RecyclerView

        // Observe shopping list data and update the RecyclerView when data changes
        viewModel.getAllShoppingListItem().observe(this, Observer {
            adapter.items = it // Updates the adapter's data with the new list
            adapter.notifyDataSetChanged() // Refreshes the RecyclerView to show updated data
        })

        // Set up Floating Action Button to add new shopping items
        binding.fab.setOnClickListener {
            AddShoppingItemDailog(this, object : DialogListener {
                // Handles item addition when Add button is clicked in the dialog
                override fun onAddButtonClicked(item: ShoppingItem) {
                    viewModel.upsert(item) // Inserts or updates the item in the database
                }
            }).show() // Shows the dialog
        }

        /*
        Extends AppCompatActivity, which is a base class for activities that use the support library action bar.
This activity displays the shopping list and handles interactions like adding items.
Binding:

Uses ActivityShoppingBinding to access views defined in activity_shopping.xml.
binding = ActivityShoppingBinding.inflate(layoutInflater) initializes binding by inflating the layout.
setContentView(binding.root) sets the content view of the activity to the root view of the binding.
Toolbar:

setSupportActionBar(binding.toolbar) sets the toolbar as the action bar, enabling action bar features.
ViewModel Initialization:

Initializes ShoppingDatabase, ShoppingRepository, and ShoppingViewModelFactory.
ViewModelProvider uses the factory to get an instance of ShoppingListViewModel, allowing access to data and actions in the repository.
RecyclerView Setup:

Initializes ShoppingListAdapter with an empty list and the viewModel.
Sets a LinearLayoutManager to RecyclerView for a vertical list layout.
Binds the adapter to the RecyclerView, enabling it to display shopping items.
Data Observation:

Observes the shopping list items from viewModel.getAllShoppingListItem().
When data changes, adapter.items = it updates the adapter’s data, and notifyDataSetChanged() refreshes the RecyclerView with the new list.
Floating Action Button:

binding.fab.setOnClickListener adds an onClick listener to the FAB for adding new items.
Opens an AddShoppingItemDialog where the user can input new items.
When the "Add" button is clicked in the dialog, viewModel.upsert(item) adds or updates the item in the database.






        *
        *
        *
        */
    }
}
