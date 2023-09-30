package android.fundamental.githubapp.data.repository

import android.fundamental.githubapp.data.database.FavoriteDao
import android.fundamental.githubapp.data.database.FavoriteUser
import android.util.Log
import androidx.lifecycle.LiveData
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavRepository(
    private val mFavoriteDao: FavoriteDao,
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()
) {
    fun getAllFavorite(): LiveData<List<FavoriteUser>> {
        return mFavoriteDao.getAllFavUsers()
    }

    fun insert(user: FavoriteUser, favState: Boolean) {
        executorService.execute {
            user.isFavorite = favState
            mFavoriteDao.insert(user)
        }
    }

    fun delete(user: FavoriteUser, favState: Boolean) {
        executorService.execute {
            user.isFavorite = favState
            mFavoriteDao.delete(user)
            Log.d("Repository", "delete: ${user.login}")
        }
    }

    fun getFavUsername(username: String): LiveData<FavoriteUser> {
        return mFavoriteDao.getFavUsername(username)
    }
}