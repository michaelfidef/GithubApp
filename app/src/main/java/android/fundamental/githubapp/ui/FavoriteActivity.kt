package android.fundamental.githubapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.fundamental.githubapp.R
import android.fundamental.githubapp.databinding.ActivityFavoriteBinding
import android.view.View

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFavoriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}