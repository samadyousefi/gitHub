package ir.kt.github.domain.use_case.select

import android.util.Log
import ir.kt.github.data.remote.dto.ResponseSearchUsers
import ir.kt.github.data.remote.dto.ResponseSelectUser
import ir.kt.github.data.repository.UserRepository
import ir.kt.github.util.ApiResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SelectUserUseCase @Inject constructor(private val userRepository: UserRepository) {

    operator fun invoke(userName: String): Flow<ApiResource<ResponseSelectUser>> =
        flow {
            try {
                emit(ApiResource.Loading())
                val data = userRepository.selectUser(userName)
                emit(ApiResource.Success(data))

            } catch (e: HttpException) {
                Log.d("kkk" , e.message())

                emit(ApiResource.Error("An unexpected error occured"))
            } catch (e: IOException) {
                emit(ApiResource.Error("Couldn't reach server. Check your internet connect ${e.message}"))
            }
        }
}
