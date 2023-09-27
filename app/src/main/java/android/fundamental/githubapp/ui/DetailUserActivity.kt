package android.fundamental.githubapp.ui

import android.fundamental.githubapp.R
import android.fundamental.githubapp.data.adapter.SectionsPagerAdapter
import android.fundamental.githubapp.data.response.DetailUserResponse
import android.fundamental.githubapp.databinding.ActivityDetailUserBinding
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
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

        detailViewModel.detailUser.observe(this){detailUser ->
            setDetailUser(detailUser)
        }

        val username = intent.getStringExtra(EXTRA_LOGIN) ?: ""
        binding.textView.text = username

        detailViewModel.getDetail(username)
        detailViewModel.isLoading.observe(this) { isLoading ->
            showLoading(isLoading)
        }

        val sectionsPagerAdapter = SectionsPagerAdapter(this, username)
        val viewPager : ViewPager2 = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs : TabLayout = binding.tabs
        TabLayoutMediator(tabs, viewPager){tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        supportActionBar?.elevation = 0f
    }

    private fun setDetailUser(detailUser: DetailUserResponse){
        binding.textView.text = detailUser.login
        binding.textView2.text = detailUser.name
        Glide.with(binding.root.context)
            .load(detailUser.avatarUrl)
            .into(binding.imageView)
        binding.followers.text = StringBuilder(detailUser.followers.toString()).append(" Followers")
        binding.following.text = StringBuilder(detailUser.following.toString()).append(" Followings")
    }
    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}