package android.fundamental.githubapp.data.repository

import android.fundamental.githubapp.data.database.FavoriteDao
import android.fundamental.githubapp.data.database.FavoriteUser
import android.fundamental.githubapp.data.retrofit.ApiService
import androidx.lifecycle.MediatorLiveData
import java.util.concurrent.Executors

class FavRepository private constructor(
    private val apiService: ApiService,
    private val favoriteDao: FavoriteDao,
    private val appExecutors: Executors
){
    private val result = MediatorLiveData<Result<List<FavoriteUser>>>()

}