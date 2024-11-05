import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.data.db.entity.ShoppingItem
import com.example.shoppinglist.databinding.ShoppingItemBinding
import com.example.shoppinglist.uiPackn.shoppinglist.ShoppingListViewModel

class ShoppingListAdapter(
    var items: List<ShoppingItem>, // List of shopping items to display
    private val viewModel: ShoppingListViewModel // ViewModel for managing data and operations
) : RecyclerView.Adapter<ShoppingListAdapter.ShoppingListViewHolder>() {

    // ViewHolder class to hold the view for each shopping item
    inner class ShoppingListViewHolder(private val binding: ShoppingItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ShoppingItem) { // Binds the ShoppingItem to the view
            binding.tvName.text = item.name // Set the item name in the TextView
            binding.tvAmount.text = item.amount.toString() // Display the amount as a String

            // Delete button click listener
            binding.ivDelete.setOnClickListener {
                viewModel.delete(item) // Calls delete method in the ViewModel
            }

            // Increment button click listener
            binding.ivPlus.setOnClickListener {
                item.amount++ // Increment the item's amount
                viewModel.upsert(item) // Update the item in the ViewModel
            }

            // Decrement button click listener
            binding.ivMinus.setOnClickListener {
                if (item.amount > 0) { // Check if amount is greater than 0
                    item.amount-- // Decrement the item's amount
                    viewModel.upsert(item) // Update the item in the ViewModel
                }
            }
        }
    }

    // Creates a new ViewHolder for each item in the RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListViewHolder {
        // Inflate the layout for a single shopping item
        val binding = ShoppingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShoppingListViewHolder(binding) // Return a new ViewHolder
    }

    // Returns the total number of items in the adapter
    override fun getItemCount(): Int {
        return items.size // Returns the size of the item list
    }

    // Binds the ViewHolder to a specific position in the item list
    override fun onBindViewHolder(holder: ShoppingListViewHolder, position: Int) {
        val currentShoppingItem = items[position] // Get the current ShoppingItem
        holder.bind(currentShoppingItem) // Bind the current item to the ViewHolder
    }
}
/*
* Imports:

The necessary classes and libraries are imported for creating the adapter, managing the layout, and binding data.
Class Declaration:

kotlin
Copy code
class ShoppingListAdapter(
    var items: List<ShoppingItem>, // List of shopping items to display
    private val viewModel: ShoppingListViewModel // ViewModel for managing data and operations
) : RecyclerView.Adapter<ShoppingListAdapter.ShoppingListViewHolder>() {
ShoppingListAdapter extends RecyclerView.Adapter and uses a nested ShoppingListViewHolder class for each item.
The constructor accepts a list of ShoppingItem objects and a ShoppingListViewModel.
ViewHolder Class:

kotlin
Copy code
inner class ShoppingListViewHolder(private val binding: ShoppingItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
The ShoppingListViewHolder class holds the UI components for each shopping item using data binding (ShoppingItemBinding).
The bind method is defined to populate the views with data from a ShoppingItem.
Binding Data:

Within the bind method:
The item's name and amount are set to the respective views.
Click listeners are set up for the delete and increment/decrement buttons, invoking methods in the ShoppingListViewModel to modify the data.
onCreateViewHolder:

kotlin
Copy code
override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListViewHolder {
    val binding = ShoppingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return ShoppingListViewHolder(binding)
}
This method is called when a new ViewHolder needs to be created. It inflates the layout for a single shopping item and returns a new ShoppingListViewHolder.
getItemCount:

kotlin
Copy code
override fun getItemCount(): Int {
    return items.size
}
This method returns the number of items in the adapter, which is the size of the items list.
onBindViewHolder:

kotlin
Copy code
override fun onBindViewHolder(holder: ShoppingListViewHolder, position: Int) {
    val currentShoppingItem = items[position]
    holder.bind(currentShoppingItem)
}
This method is called to display the data at a specified position. It retrieves the ShoppingItem for that position and binds it to the ViewHolder.
Summary
The ShoppingListAdapter is a crucial component for displaying a list of shopping items in a RecyclerView. It manages the creation and binding of ViewHolder instances that display individual items. The adapter allows for interactive functionality, such as deleting items and updating their amounts, by utilizing a ShoppingListViewModel to handle the underlying data operations. This separation of concerns (UI management in the adapter, data management in the ViewModel) follows the MVVM architecture pattern, promoting better organization and maintainability of the code.






*/