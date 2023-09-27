package android.fundamental.githubapp.ui

import android.fundamental.githubapp.data.response.DetailUserResponse
import android.fundamental.githubapp.data.retrofit.ApiConfig
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel : ViewModel(){

    private val _detailUser = MutableLiveData<DetailUserResponse>()
    val detailUser: LiveData<DetailUserResponse> = _detailUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        private const val TAG = "detailViewModel"
    }

    init {
        getDetail()
    }

    fun getDetail(username : String = ""){
        _isLoading.value = true
        val client = ApiConfig.getApiConfig().detailUser(username)
        client.enqueue(object : Callback<DetailUserResponse>{
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ){
                _isLoading.value = false
                if (response.isSuccessful){
                    val responseBody = response.body()
                    if (responseBody != null){
                        _detailUser.value = response.body()
                        Log.d(TAG, "onResponse: ")
                    }
                }else{
                    Log.e(TAG, "onFailure: ${response.message()}" )
                }
            }
            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("TAG", "onFailure: ${t.message}")
            }
        })
    }
}