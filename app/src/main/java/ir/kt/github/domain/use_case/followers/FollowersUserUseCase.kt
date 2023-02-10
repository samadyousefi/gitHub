package ir.kt.github.domain.use_case.followers

import ir.kt.github.data.remote.dto.ResponseSearchUsers
import ir.kt.github.data.repository.UserRepository
import ir.kt.github.util.ApiResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class FollowersUserUseCase @Inject constructor(private val userRepository: UserRepository) {

    operator fun invoke(userName: String): Flow<ApiResource<List<ResponseSearchUsers.ItemUser>>> =
        flow {
            try {
                emit(ApiResource.Loading())
                val data = userRepository.followersUser(userName)
                emit(ApiResource.Success(data))

            } catch (e: HttpException) {
                emit(ApiResource.Error("An unexpected error occured"))
            } catch (e: IOException) {
                emit(ApiResource.Error("Couldn't reach server. Check your internet connect ${e.message}"))
            }
        }
}
