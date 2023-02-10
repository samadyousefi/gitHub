package ir.kt.github.domain.use_case.check_fav

import ir.kt.github.data.repository.UserRepository
import javax.inject.Inject

class CheckUserFavoriteUseCase @Inject constructor(private val repository: UserRepository) {
   suspend operator fun invoke(id:Int) : Boolean{
     return  repository.checkUserInFavorite(id)
   }
}