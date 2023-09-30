package android.fundamental.githubapp.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: FavoriteUser)

    @Delete
    fun delete(user: FavoriteUser)

    @Query("SELECT * from User ORDER BY id ASC")
    fun getAllFavUsers(): LiveData<List<FavoriteUser>>

    @Query("SELECT * FROM user WHERE login = :username AND is_favorite = 1")
    fun getFavUsername(username: String): LiveData<FavoriteUser>
}