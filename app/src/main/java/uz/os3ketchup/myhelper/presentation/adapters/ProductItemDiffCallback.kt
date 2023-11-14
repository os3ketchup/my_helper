package uz.os3ketchup.myhelper.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import uz.os3ketchup.myhelper.domain.Product
import uz.os3ketchup.myhelper.domain.User

class ProductItemDiffCallback : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}