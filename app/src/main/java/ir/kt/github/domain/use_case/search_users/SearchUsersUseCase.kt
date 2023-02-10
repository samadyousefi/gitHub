package ir.kt.github.domain.use_case.search_users

import ir.kt.github.data.remote.dto.ResponseSearchUsers
import ir.kt.github.data.repository.UserRepository
import ir.kt.github.util.ApiResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SearchUsersUseCase @Inject constructor(private val userRepository: UserRepository) {


//    suspend operator fun invoke(perPage:Int,page: Int , name:String): ResponseSearchUsers {
//        return userRepository.searchUsers(perPage =perPage , pageIndex =  page , name =name)
//    }

    operator fun invoke(name: String): Flow<ApiResource<ResponseSearchUsers>> = flow {
        try {
            emit(ApiResource.Loading())
            val data = userRepository.searchUsers(name)
            emit(ApiResource.Success(data))

        } catch (e: HttpException) {
            emit(ApiResource.Error("An unexpected error occured"))
        } catch (e: IOException) {
            emit(ApiResource.Error("Couldn't reach server. Check your internet connect ${e.message}"))
        }
    }
}
