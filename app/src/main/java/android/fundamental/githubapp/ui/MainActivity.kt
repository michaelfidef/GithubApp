package android.fundamental.githubapp.ui

import android.fundamental.githubapp.data.adapter.UserAdapter
import android.fundamental.githubapp.data.response.ItemsItem
import android.fundamental.githubapp.data.response.UserResponse
import android.fundamental.githubapp.data.retrofit.ApiConfig
import android.fundamental.githubapp.databinding.ActivityMainBinding
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val layoutManager = LinearLayoutManager(this)
        binding.rvUser.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvUser.addItemDecoration(itemDecoration)

        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { _, _, _ ->
                    searchBar.text = searchView.text
                    val searchVal = searchBar.text
                    searchView.hide()
                    showLoading(true)
                    getSearchUser(searchVal.toString())
                    Toast.makeText(this@MainActivity, searchView.text, Toast.LENGTH_SHORT).show()
                    false
                }
        }
        getUser()
    }

        private fun getUser(){
            showLoading(true)
            val client = ApiConfig.getApiConfig().searchGitHubUser(GITHUB)
            client.enqueue(object : Callback<UserResponse>{
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ){
                    showLoading(false)
                    if (response.isSuccessful){
                        val responseBody = response.body()
                        if (responseBody != null){
                            setAccountUser(responseBody.items)
                        }
                    }else{
                        Log.e(TAG, "onFailure: ${response.message()}" )
                    }
                }
                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e("TAG", "onFailure: ${t.message}")
                }
            })
        }

    private fun getSearchUser(q : String){
        showLoading(true)
        val client = ApiConfig.getApiConfig().searchGitHubUser(q)
        client.enqueue(object : Callback<UserResponse>{
            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ){
                showLoading(false)
                if (response.isSuccessful){
                    val responseBody = response.body()
                    if (responseBody != null){
                        setAccountUser(responseBody.items)
                        Log.d(TAG, "onResponse: ")
                    }
                }else{
                    Log.e(TAG, "onFailure: ${response.message()}" )
                }
            }
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e("TAG", "onFailure: ${t.message}")
            }
        })
    }

    private fun setAccountUser(account: List<ItemsItem>) {
        val adapter = UserAdapter()
        adapter.submitList(account)
        binding.rvUser.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    companion object {
        private const val GITHUB = "michael"
        private const val TAG = "MainViewModel"
    }
}