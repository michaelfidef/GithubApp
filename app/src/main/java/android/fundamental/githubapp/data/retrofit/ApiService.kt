package android.fundamental.githubapp.data.retrofit

import android.fundamental.githubapp.data.response.DetailUserResponse
import android.fundamental.githubapp.data.response.ItemsItem
import android.fundamental.githubapp.data.response.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    fun searchGitHubUser(@Query("q") query: String): Call<UserResponse>

    @GET("users/{username}")
    fun detailUser(@Path("username") username: String): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    fun getFollowers(@Path("username") username: String): Call<List<ItemsItem>>

    @GET("users/{username}/following")
    fun getFollowing(@Path("username") username: String): Call<List<ItemsItem>>
}