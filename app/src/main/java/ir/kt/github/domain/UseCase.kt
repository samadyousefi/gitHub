package ir.kt.github.domain

import ir.kt.github.domain.use_case.check_fav.CheckUserFavoriteUseCase
import ir.kt.github.domain.use_case.fav_list.GetAllFavoriteUseCase
import ir.kt.github.domain.use_case.followers.FollowersUserUseCase
import ir.kt.github.domain.use_case.following.FollowingUserUseCase
import ir.kt.github.domain.use_case.remove_fav.RemoveUserFavoriteUseCase
import ir.kt.github.domain.use_case.repos.ReposUserUseCase
import ir.kt.github.domain.use_case.search_users.SearchUsersUseCase
import ir.kt.github.domain.use_case.select.SelectUserUseCase
import ir.kt.github.domain.use_case.user_fav.InsertUserFavoriteUseCase

data class UseCase(
    val searchUsersUseCase: SearchUsersUseCase,
    val insertUserFavoriteUseCase: InsertUserFavoriteUseCase ,
    val selectUserUseCase: SelectUserUseCase ,
    val followersUserUseCase: FollowersUserUseCase,
    val followingUserUseCase: FollowingUserUseCase ,
    val checkUserFavoriteUseCase: CheckUserFavoriteUseCase ,
    val getAllFavoriteUseCase: GetAllFavoriteUseCase,
    val removeUserFavoriteUseCase: RemoveUserFavoriteUseCase,
    val reposUserUseCase: ReposUserUseCase
)
