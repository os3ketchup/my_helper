package uz.os3ketchup.myhelper.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import uz.os3ketchup.myhelper.R
import uz.os3ketchup.myhelper.databinding.ItemProductBinding
import uz.os3ketchup.myhelper.domain.Product

class ProductListAdapter : ListAdapter<Product, ProductItemViewHolder>(ProductItemDiffCallback()) {

    var onProductItemClickListener: ((Product) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemViewHolder {

        return ProductItemViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) {
        val binding = holder.item
        val product = getItem(position)
        binding.root.setOnClickListener {
            onProductItemClickListener?.invoke(product)
        }
        binding.tvProductName.text = product.name
        binding.tvPrice.text = product.price
        binding.tvUnit.text = product.unit


    }

    companion object {
        val VIEW_TYPE_PRODUCT = R.layout.item_product
        const val MAX_POOL_SIZE = 30
    }
}