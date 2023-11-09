package uz.os3ketchup.myhelper.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import uz.os3ketchup.myhelper.domain.Category
import uz.os3ketchup.myhelper.domain.User

class CategoryItemDiffCallback : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }
}