package ir.kt.github.data.loacl

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [UserEntity::class],
    version = 1
)
abstract class UsersDatabase : RoomDatabase() {
    abstract val dao: UsersDao

    companion object {
        const val DATABASE_NAME = "user_db"
    }
}