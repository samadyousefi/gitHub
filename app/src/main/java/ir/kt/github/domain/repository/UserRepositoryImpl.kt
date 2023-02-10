package ir.kt.github.domain.repository

import ir.kt.github.data.loacl.UserEntity
import ir.kt.github.data.loacl.UsersDao
import ir.kt.github.data.remote.UserApi
import ir.kt.github.data.remote.dto.ResponseRepos
import ir.kt.github.data.remote.dto.ResponseSearchUsers
import ir.kt.github.data.remote.dto.ResponseSelectUser
import ir.kt.github.data.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val api: UserApi, private val dao: UsersDao) :
    UserRepository {
    override suspend fun insertUserInFavorite(userEntity: UserEntity) {
        return dao.insertUserToFavorite(userEntity)
    }

    override suspend fun searchUsers(name: String): ResponseSearchUsers {
        return api.searchUsers(name)
    }

    override suspend fun selectUser(userName: String): ResponseSelectUser {
        return api.selectUser(userName)
    }

    override suspend fun followingUser(userName: String): List<ResponseSearchUsers.ItemUser> {
        return api.followingUser(userName)
    }

    override suspend fun followersUser(userName: String): List<ResponseSearchUsers.ItemUser> {
        return api.followersUser(userName)
    }

    override suspend fun reposUser(userName: String): ResponseRepos {
        return api.reposUser(userName)
    }

    override suspend fun checkUserInFavorite(id: Int): Boolean {
        return dao.checkIsFavoriteUser(id = id)
    }

    override suspend fun removeUserInFavorite(id: Int) {
        return dao.removeUserInFav(id)
    }

    override suspend fun getAllUserInFavorite(): List<UserEntity> {
        return dao.getAllFavorite()
    }

//    override suspend fun searchUsers(
//        perPage: Int,
//        pageIndex: Int,
//        name: String
//    ): ResponseSearchUsers {
//        return api.searchUsers(perPage =  perPage , pageIndex =pageIndex,name)
//    }


}