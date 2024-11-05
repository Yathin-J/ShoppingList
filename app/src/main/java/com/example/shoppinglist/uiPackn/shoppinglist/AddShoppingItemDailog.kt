package com.example.shoppinglist.uiPackn.shoppinglist

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.shoppinglist.data.db.entity.ShoppingItem
import com.example.shoppinglist.databinding.DailogListBinding

// Custom dialog for adding a new shopping item
class AddShoppingItemDailog(context: Context, var addDialogListener: DialogListener) : AppCompatDialog(context) {

    // View binding for accessing views in dialog_list.xml
    private lateinit var binding: DailogListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using binding and set the dialog content view
        binding = DailogListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the "Add" button click listener
        binding.tvAdd.setOnClickListener {
            // Get the item name and amount from EditText fields
            val name = binding.etName.text.toString().trim() // Get item name
            val amountString = binding.etAmount.text.toString().trim() // Get item amount as a string

            // Check if any field is empty
            if (name.isEmpty() || amountString.isEmpty()) {
                Toast.makeText(context, "Please enter all the information", Toast.LENGTH_SHORT).show()
                return@setOnClickListener // Exit early if fields are empty
            }

            // Convert amount string to an Int, handling invalid input
            val amount = amountString.toIntOrNull()
            if (amount == null) { // Check if conversion is successful
                Toast.makeText(context, "Please enter a valid amount", Toast.LENGTH_SHORT).show()
                return@setOnClickListener // Exit if amount is not valid
            }

            // Create a ShoppingItem instance with the provided name and amount
            val item = ShoppingItem(null, name, amount)

            // Notify listener that the item is ready to be added
            addDialogListener.onAddButtonClicked(item)

            // Dismiss the dialog after adding the item
            dismiss()
        }

        // Set up the "Cancel" button click listener to dismiss the dialog
        binding.tvCancel.setOnClickListener {
            cancel() // Close the dialog without taking any action
        }
    }
}
/*
* Accepts context to initialize the dialog.
Accepts addDialogListener, which is an instance of DialogListener. This listener allows ShoppingListActivity (or any class implementing DialogListener) to respond when the "Add" button is clicked.
Binding Initialization:

Uses DailogListBinding to access views in dialog_list.xml without needing findViewById.
onCreate Method:

Calls DailogListBinding.inflate(layoutInflater) to inflate the dialog layout.
setContentView(binding.root) sets the inflated layout as the content view for the dialog.
Add Button (binding.tvAdd.setOnClickListener):

Retrieves text from etName and etAmount input fields.
Checks if either field is empty. If so, shows a Toast message asking the user to enter all information.
Parses amountString to an integer (amount). If the conversion fails (e.g., user entered non-numeric text), shows a Toast message asking for a valid amount.
Creates a ShoppingItem instance with the provided name and amount, using null for the ID (it will be auto-generated).
Calls addDialogListener.onAddButtonClicked(item) to notify the listener that the item is ready to be added.
Dismisses the dialog after successfully creating the item.
Cancel Button (binding.tvCancel.setOnClickListener):

Calls cancel() to close the dialog without performing any actions.





*/
