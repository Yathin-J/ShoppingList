import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.data.db.entity.ShoppingItem
import com.example.shoppinglist.databinding.ShoppingItemBinding
import com.example.shoppinglist.uiPackn.shoppinglist.ShoppingListViewModel

class ShoppingListAdapter(
    var items: List<ShoppingItem>,
    private val viewModel: ShoppingListViewModel
) : RecyclerView.Adapter<ShoppingListAdapter.ShoppingListViewHolder>() {

    inner class ShoppingListViewHolder(private val binding: ShoppingItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ShoppingItem) {
            binding.tvName.text = item.name
            binding.tvAmount.text = item.amount.toString() // Ensure amount is a String
            binding.ivDelete.setOnClickListener {
                viewModel.delete(item)
            }
            binding.ivPlus.setOnClickListener {
                item.amount++
                viewModel.upsert(item)
            }
            binding.ivMinus.setOnClickListener {
                if (item.amount > 0) {
                    item.amount--
                    viewModel.upsert(item)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListViewHolder {
        val binding = ShoppingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShoppingListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ShoppingListViewHolder, position: Int) {
        val currentShoppingItem = items[position]
        holder.bind(currentShoppingItem)
    }
}
