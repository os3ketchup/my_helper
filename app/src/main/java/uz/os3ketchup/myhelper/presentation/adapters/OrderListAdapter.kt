package uz.os3ketchup.myhelper.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import uz.os3ketchup.myhelper.R
import uz.os3ketchup.myhelper.databinding.ItemOrderBinding
import uz.os3ketchup.myhelper.databinding.ItemProductBinding
import uz.os3ketchup.myhelper.domain.Order
import uz.os3ketchup.myhelper.domain.Product

class OrderListAdapter : ListAdapter<Order, OrderItemViewHolder>(OrderItemDiffCallback()) {

    var onOrderItemClickListener: ((Order) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderItemViewHolder {

        return OrderItemViewHolder(
            ItemOrderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OrderItemViewHolder, position: Int) {
        val binding = holder.item
        val order = getItem(position)
        binding.root.setOnClickListener {
            onOrderItemClickListener?.invoke(order)
        }

        val totalPrice = order.list.sumOf {
            (it.price.toInt()*it.amount.toInt()).toLong()
        }
        binding.tvTotal.text = totalPrice.toString()

    }

    companion object {
        val VIEW_TYPE_ORDERS = R.layout.item_order
        const val MAX_POOL_SIZE = 30
    }
}