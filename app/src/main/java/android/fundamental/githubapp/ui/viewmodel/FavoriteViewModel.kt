package android.fundamental.githubapp.ui.viewmodel

import android.app.Application
import android.fundamental.githubapp.data.database.FavoriteRoomDatabase
import android.fundamental.githubapp.data.database.FavoriteUser
import android.fundamental.githubapp.data.repository.FavRepository
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: FavRepository =
        FavRepository(FavoriteRoomDatabase.getInstance(application).favoriteDao())

    var favoriteUser: LiveData<List<FavoriteUser>> = repository.getAllFavorite()

    fun insert(user: FavoriteUser) {
        repository.insert(user, true)
    }

    fun delete(user: FavoriteUser) {
        repository.delete(user, true)
    }

    fun getFavUsername(user: String): LiveData<FavoriteUser> {
        return repository.getFavUsername(user)
    }
}