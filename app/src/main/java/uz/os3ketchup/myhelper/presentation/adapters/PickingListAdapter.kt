package uz.os3ketchup.myhelper.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import uz.os3ketchup.myhelper.R
import uz.os3ketchup.myhelper.databinding.ItemOrderBinding
import uz.os3ketchup.myhelper.databinding.ItemPickProductBinding
import uz.os3ketchup.myhelper.databinding.ItemProductBinding
import uz.os3ketchup.myhelper.domain.Order
import uz.os3ketchup.myhelper.domain.Product

class PickingListAdapter : ListAdapter<Product, PickingItemViewHolder>(PickingItemDiffCallback()) {

    var onPickingItemClickListener: ((Product) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PickingItemViewHolder {
        return PickingItemViewHolder(
            ItemPickProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PickingItemViewHolder, position: Int) {
        val binding = holder.item
        val product = getItem(position)
        binding.root.setOnClickListener {
            onPickingItemClickListener?.invoke(product)
        }
        binding.tvProductName.text = product.name
        binding.tvAmount.text = product.amount
        binding.tvPrice.text = product.price.toString()
    }

    companion object {
        val VIEW_TYPE_PICKS = R.layout.item_pick_product
        const val MAX_POOL_SIZE = 30
    }
}