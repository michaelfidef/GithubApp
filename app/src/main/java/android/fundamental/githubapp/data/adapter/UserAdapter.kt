package android.fundamental.githubapp.data.adapter

import android.content.Intent
import android.fundamental.githubapp.data.response.ItemsItem
import android.fundamental.githubapp.databinding.ItemUserBinding
import android.fundamental.githubapp.ui.DetailUserActivity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class UserAdapter : ListAdapter<ItemsItem, UserAdapter.UserViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val list = getItem(position)
        holder.bind(list)
        holder.itemView.setOnClickListener {
            val detailIntent = Intent(holder.itemView.context, DetailUserActivity::class.java)
            detailIntent.putExtra(DetailUserActivity.EXTRA_LOGIN, list.login)
            holder.itemView.context.startActivities(arrayOf(detailIntent))
        }
    }

    class UserViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: ItemsItem) {
            Glide.with(binding.root.context)
                .load(user.avatarUrl).into(binding.profileImage)
            binding.profileName.text = user.login
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemsItem>() {
            override fun areContentsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }

            override fun areItemsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}