package ir.kt.github.data.remote

import ir.kt.github.data.remote.dto.ResponseSearchUsers
import ir.kt.github.data.remote.dto.ResponseSelectUser
import ir.kt.github.util.ApiResource

class FakeUserApi : UserApi {
    private val userList = listOf(
        ResponseSearchUsers.ItemUser("","",""
        , "" , "" , "" , "" ,1 ,"sam" ,"","","",""
        ,1,false ,"","","","") ,

        ResponseSearchUsers.ItemUser("","",""
            , "" , "" , "" , "" ,1 ,"inter" ,"","","",""
            ,1,false ,"","","","")
        )

    override suspend fun searchUsers(q: String?): ResponseSearchUsers {
        return searchUsers(q)
    }

    override suspend fun selectUser(username: String): ResponseSelectUser {
        TODO("Not yet implemented")
    }

    override suspend fun followingUser(username: String): List<ResponseSearchUsers.ItemUser> {
        TODO("Not yet implemented")
    }

    override suspend fun followersUser(username: String): List<ResponseSearchUsers.ItemUser> {
        TODO("Not yet implemented")
    }

    override suspend fun reposUser(username: String): Int {
        TODO("Not yet implemented")
    }

    private fun searchUser(name:String) : List<ResponseSearchUsers.ItemUser> {
        val users = mutableListOf<ResponseSearchUsers.ItemUser>()
        return if (name.isNotEmpty()){
            userList.forEach { user ->
                if (user.login.lowercase().contains(name.lowercase())){
                    users.add(user)
                }
            }
            users
        }else {
            emptyList()
        }
    }
}