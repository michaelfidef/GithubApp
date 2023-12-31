package android.fundamental.githubapp.ui.view

import android.content.Intent
import android.fundamental.githubapp.R
import android.fundamental.githubapp.data.adapter.SectionsPagerAdapter
import android.fundamental.githubapp.data.database.FavoriteUser
import android.fundamental.githubapp.data.response.DetailUserResponse
import android.fundamental.githubapp.databinding.ActivityDetailUserBinding
import android.fundamental.githubapp.ui.viewmodel.DetailViewModel
import android.fundamental.githubapp.ui.viewmodel.FavoriteViewModel
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailUserActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_LOGIN = "extra_login"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
    }

    private lateinit var binding: ActivityDetailUserBinding
    private val detailViewModel: DetailViewModel by viewModels<DetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mFavoriteViewModel = ViewModelProvider(this)[FavoriteViewModel::class.java]
        detailViewModel.detailUser.observe(this) { detailUser ->
            setDetailUser(detailUser)
            var isFavorite = false
            detailUser.login.let {
                mFavoriteViewModel.getFavUsername(it).observe(this) { favoriteUser ->
                    if (favoriteUser != null && favoriteUser.isFavorite) {
                        isFavorite = true
                        binding.fabFav.setImageResource(R.drawable.ic_fav_full)
                    } else {
                        isFavorite = false
                        binding.fabFav.setImageResource(R.drawable.ic_fav_null)
                    }
                }
            }
            binding.fabFav.setOnClickListener {
                val insert = FavoriteUser(
                    id = detailUser.id,
                    login = detailUser.login,
                    avatarUrl = detailUser.avatarUrl,
                    isFavorite = !isFavorite
                )
                val delete = FavoriteUser(
                    id = detailUser.id,
                    login = detailUser.login,
                    avatarUrl = detailUser.avatarUrl,
                    isFavorite = isFavorite
                )
                if (isFavorite) {
                    mFavoriteViewModel.delete(delete)
                    Toast.makeText(this, "User dihapus dari Favorit", Toast.LENGTH_SHORT).show()

                } else {
                    mFavoriteViewModel.insert(insert)
                    Toast.makeText(this, "User ditambahkan ke Favorit", Toast.LENGTH_SHORT).show()
                }
                isFavorite = !isFavorite
            }
        }

        val username = intent.getStringExtra(EXTRA_LOGIN) ?: ""
        binding.textView.text = username

        detailViewModel.getDetail(username)
        detailViewModel.isLoading.observe(this) { isLoading ->
            showLoading(isLoading)
        }

        val sectionsPagerAdapter = SectionsPagerAdapter(this, username)
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        supportActionBar?.elevation = 0f

        supportActionBar?.apply {
            title = "Detail User Github"
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setDetailUser(detailUser: DetailUserResponse) {
        binding.textView.text = detailUser.login
        binding.textView2.text = detailUser.name
        Glide.with(binding.root.context)
            .load(detailUser.avatarUrl)
            .into(binding.imageView)
        binding.followers.text = StringBuilder(detailUser.followers.toString()).append(" Followers")
        binding.following.text =
            StringBuilder(detailUser.following.toString()).append(" Followings")
    }

    override fun onBackPressed() {
        startActivity(Intent(this@DetailUserActivity, MainActivity::class.java))
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                startActivity(Intent(this@DetailUserActivity, MainActivity::class.java))
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }


    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}