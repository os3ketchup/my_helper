package uz.os3ketchup.myhelper.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import uz.os3ketchup.myhelper.domain.User

class UserItemDiffCallback:DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.uId==newItem.uId
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem==newItem
    }
}