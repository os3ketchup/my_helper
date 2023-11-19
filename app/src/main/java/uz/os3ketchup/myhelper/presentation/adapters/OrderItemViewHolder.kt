package uz.os3ketchup.myhelper.presentation.adapters

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import uz.os3ketchup.myhelper.databinding.ItemCategoryBinding
import uz.os3ketchup.myhelper.databinding.ItemOrderBinding
import uz.os3ketchup.myhelper.databinding.ItemUserBinding

class OrderItemViewHolder(
    var item: ItemOrderBinding
) : RecyclerView.ViewHolder(item.root)