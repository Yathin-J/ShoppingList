package com.example.shoppinglist.uiPackn.shoppinglist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.shoppinglist.R
import com.example.shoppinglist.data.db.entity.ShoppingItem
import com.example.shoppinglist.databinding.DailogListBinding

class AddShoppingItemDailog(context: Context, var addDialogListener: DialogListener) : AppCompatDialog(context) {

    private lateinit var binding: DailogListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DailogListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvAdd.setOnClickListener {
            val name = binding.etName.text.toString().trim() // Get the text from EditText
            val amountString = binding.etAmount.text.toString().trim() // Get the text from EditText

            if (name.isEmpty() || amountString.isEmpty()) {
                Toast.makeText(context, "Please enter all the information", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Parse the amount to an Int, and handle possible NumberFormatException
            val amount = amountString.toIntOrNull()
            if (amount == null) {
                Toast.makeText(context, "Please enter a valid amount", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val item = ShoppingItem(null, name, amount)
            addDialogListener.onAddButtonClicked(item)  // Use the instance to call the method
            dismiss()
        }

        binding.tvCancel.setOnClickListener {
            cancel()
        }
    }
}
