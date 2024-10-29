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

class ShoppingListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShoppingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the binding and set it as the content view
        binding = ActivityShoppingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the toolbar
        setSupportActionBar(binding.toolbar)

        // Initialize ViewModel and Adapter
        val database = ShoppingDatabase.getInstance(this)
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, factory).get(ShoppingListViewModel::class.java)

        val adapter = ShoppingListAdapter(listOf(), viewModel)

        binding.rvShoppingList.layoutManager = LinearLayoutManager(this)
        binding.rvShoppingList.adapter = adapter

        viewModel.getAllShoppingListItem().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        // Floating Action Button setup (Uncomment if FAB is required)
        binding.fab.setOnClickListener {
            AddShoppingItemDailog(this, object : DialogListener {
                override fun onAddButtonClicked(item: ShoppingItem) {
                    viewModel.upsert(item)
                }
            }).show()
        }
    }
}
