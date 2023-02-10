package ir.kt.github.domain.use_case.remove_fav

import ir.kt.github.data.repository.UserRepository
import javax.inject.Inject

class RemoveUserFavoriteUseCase @Inject constructor(private val repository: UserRepository) {
   suspend operator fun invoke(id:Int){
       repository.removeUserInFavorite(id)
   }
}