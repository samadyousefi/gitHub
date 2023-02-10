package ir.kt.github.data.loacl

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UsersDao {

    @Insert
    suspend fun insertUserToFavorite(userEntity: UserEntity)

    @Query("SELECT EXISTS(SELECT * FROM users WHERE id = :id)")
    suspend fun checkIsFavoriteUser(id: Int): Boolean

    @Query("DELETE FROM users WHERE id =:id")
    suspend fun removeUserInFav(id: Int)

    @Query("SELECT * FROM users")
    suspend fun getAllFavorite(): List<UserEntity>

}

