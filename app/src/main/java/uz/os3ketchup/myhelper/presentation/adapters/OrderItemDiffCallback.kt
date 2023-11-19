package uz.os3ketchup.myhelper.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import uz.os3ketchup.myhelper.domain.Order

class OrderItemDiffCallback : DiffUtil.ItemCallback<Order>() {
    override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean {
        return oldItem == newItem
    }
}