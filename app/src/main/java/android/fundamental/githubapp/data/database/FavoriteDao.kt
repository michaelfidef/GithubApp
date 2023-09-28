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
    fun insert(note: FavoriteUser)
    @Delete
    fun delete(note: FavoriteUser)
    @Query("SELECT * from favoriteuser ORDER BY username ASC")
    fun getAllNotes(): LiveData<List<FavoriteUser>>
}