package android.fundamental.githubapp.data.adapter

import android.content.Intent
import android.fundamental.githubapp.data.database.FavoriteUser
import android.fundamental.githubapp.databinding.ItemUserBinding
import android.fundamental.githubapp.ui.view.DetailUserActivity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class FavoriteAdapter(private val onClick: (FavoriteUser) -> Unit) :
    ListAdapter<FavoriteUser, FavoriteAdapter.FavoriteViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val item = getItem(position)
        val username = item.login

        holder.bind(item)
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailUserActivity::class.java).apply {
                this.putExtra(DetailUserActivity.EXTRA_LOGIN, username)
            }
            holder.run {
                username.also { SectionsPagerAdapter.username = it }
                itemView.context.startActivity(intent)
            }
        }
    }

    class FavoriteViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FavoriteUser) {
            binding.profileName.text = item.login
            Glide.with(binding.root)
                .load(item.avatarUrl)
                .into(binding.profileImage)
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<FavoriteUser> =
            object : DiffUtil.ItemCallback<FavoriteUser>() {
                override fun areItemsTheSame(
                    oldItem: FavoriteUser,
                    newItem: FavoriteUser
                ): Boolean {
                    return oldItem.login == newItem.login
                }

                override fun areContentsTheSame(
                    oldItem: FavoriteUser,
                    newItem: FavoriteUser
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}