package uz.os3ketchup.myhelper.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import uz.os3ketchup.myhelper.R
import uz.os3ketchup.myhelper.databinding.ItemUserBinding
import uz.os3ketchup.myhelper.domain.User

class UserListAdapter : ListAdapter<User, UserItemViewHolder>(UserItemDiffCallback()) {

    var onUserItemClickListener: ((User) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemViewHolder {

        return UserItemViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
        val binding = holder.item
        val user = getItem(position)
        binding.root.setOnClickListener {
            onUserItemClickListener?.invoke(user)
        }
        binding.tvLabelUser.text = user.name

    }

    companion object {
        val VIEW_TYPE_USER = R.layout.item_user
        const val MAX_POOL_SIZE = 30
    }
}