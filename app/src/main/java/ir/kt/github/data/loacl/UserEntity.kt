package ir.kt.github.data.loacl

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    val id:Int,
    val name:String,
    val avatarUrl:String,
    val location:String,
    val company:String,
    val countFollowers:Int,
    val countFollowing:Int,
    val countRepository:Int,

    )
