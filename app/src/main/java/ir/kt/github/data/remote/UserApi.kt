package ir.kt.github.data.remote

import ir.kt.github.data.remote.dto.ResponseRepos
import ir.kt.github.data.remote.dto.ResponseSearchUsers
import ir.kt.github.data.remote.dto.ResponseSelectUser
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApi {
    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") q: String?
    ): ResponseSearchUsers

    @GET("users/{username}")
    suspend fun selectUser(
        @Path("username") username: String
    ): ResponseSelectUser

    @GET("users/{username}/following")
    suspend fun followingUser(
        @Path("username") username: String , @Query("per_page") perPage: Int=100,
    ): List<ResponseSearchUsers.ItemUser>

    @GET("users/{username}/followers")
    suspend fun followersUser(
        @Path("username") username: String , @Query("per_page") perPage: Int=100
    ): List<ResponseSearchUsers.ItemUser>

    @GET("users/{username}/repos")
    suspend fun reposUser(
        @Path("username") username: String, @Query("per_page") perPage: Int=100
    ): ResponseRepos
}