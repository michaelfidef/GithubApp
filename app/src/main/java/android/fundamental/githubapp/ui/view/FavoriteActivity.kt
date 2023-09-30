package android.fundamental.githubapp.ui.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.fundamental.githubapp.data.adapter.FavoriteAdapter
import android.fundamental.githubapp.data.response.ItemsItem
import android.fundamental.githubapp.databinding.ActivityFavoriteBinding
import android.fundamental.githubapp.ui.viewmodel.FavoriteViewModel
import android.fundamental.githubapp.ui.viewmodel.ViewModelFactory
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var mFavoriteViewModel: FavoriteViewModel
    private lateinit var mFavoriteAdapter: FavoriteAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val favoriteModelFactory = ViewModelFactory(application)
        mFavoriteViewModel =
            ViewModelProvider(this, favoriteModelFactory).get(FavoriteViewModel::class.java)

        val rv_favorite = binding.rvFavorite
        mFavoriteAdapter = FavoriteAdapter { favoriteUser ->
            val favIntent = Intent(this, DetailUserActivity::class.java)
            favIntent.putExtra("username", favoriteUser.login)
            startActivity(favIntent)
        }

        rv_favorite.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mFavoriteAdapter
        }

        mFavoriteViewModel.favoriteUser.observe(this) { favoriteUser ->
            val items = arrayListOf<ItemsItem>()
            favoriteUser.map {
                val item = ItemsItem(login = it.login, avatarUrl = it.avatarUrl, id = it.id)
                items.add(item)
            }
            mFavoriteAdapter.submitList(favoriteUser)
            mFavoriteAdapter.notifyDataSetChanged()
        }

        supportActionBar?.apply {
            title = "Favorite User Github"
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }
}