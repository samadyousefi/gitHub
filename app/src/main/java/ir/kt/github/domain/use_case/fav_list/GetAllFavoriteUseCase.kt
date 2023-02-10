package ir.kt.github.domain.use_case.fav_list

import ir.kt.github.data.loacl.UserEntity
import ir.kt.github.data.repository.UserRepository
import javax.inject.Inject

class GetAllFavoriteUseCase @Inject constructor(private val repository: UserRepository) {
   suspend operator fun invoke() : List<UserEntity>{
     return  repository.getAllUserInFavorite()
   }
}