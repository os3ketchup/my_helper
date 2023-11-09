package uz.os3ketchup.myhelper.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import uz.os3ketchup.myhelper.R
import uz.os3ketchup.myhelper.databinding.ItemCategoryBinding
import uz.os3ketchup.myhelper.databinding.ItemUserBinding
import uz.os3ketchup.myhelper.domain.Category
import uz.os3ketchup.myhelper.domain.User

class CategoryListAdapter :
    ListAdapter<Category, CategoryItemViewHolder>(CategoryItemDiffCallback()) {

    var onCategoryItemClickListener: ((Category) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {

        return CategoryItemViewHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        val binding = holder.item
        val category = getItem(position)
        binding.root.setOnClickListener {
            onCategoryItemClickListener?.invoke(category)
        }
        binding.tvCategoryName.text = category.name

    }

    companion object {
        val VIEW_TYPE_CATEGORY = R.layout.item_category
    }
}