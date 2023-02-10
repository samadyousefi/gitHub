package ir.kt.github.data.repository

import ir.kt.github.data.loacl.UserEntity
import ir.kt.github.data.remote.dto.ResponseRepos
import ir.kt.github.data.remote.dto.ResponseSearchUsers
import ir.kt.github.data.remote.dto.ResponseSelectUser

interface UserRepository {
    suspend fun insertUserInFavorite(userEntity: UserEntity)
    suspend fun searchUsers(name: String): ResponseSearchUsers
    suspend fun selectUser(userName: String): ResponseSelectUser
    suspend fun followingUser(userName: String): List<ResponseSearchUsers.ItemUser>
    suspend fun followersUser(userName: String): List<ResponseSearchUsers.ItemUser>
    suspend fun reposUser(userName: String): ResponseRepos
    suspend fun checkUserInFavorite(id: Int): Boolean
    suspend fun removeUserInFavorite(id: Int)
    suspend fun getAllUserInFavorite(): List<UserEntity>
}