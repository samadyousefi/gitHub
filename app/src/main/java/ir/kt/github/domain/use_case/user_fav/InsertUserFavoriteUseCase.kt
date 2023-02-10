package ir.kt.github.domain.use_case.user_fav

import ir.kt.github.data.loacl.UserEntity
import ir.kt.github.data.repository.UserRepository
import javax.inject.Inject

class InsertUserFavoriteUseCase @Inject constructor(private val repository: UserRepository) {
   suspend operator fun invoke(userEntity: UserEntity){
       repository.insertUserInFavorite(userEntity)
   }
}