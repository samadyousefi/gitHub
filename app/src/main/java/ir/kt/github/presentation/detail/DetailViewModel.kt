package ir.kt.github.presentation.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.kt.github.data.loacl.UserEntity
import ir.kt.github.data.remote.dto.ResponseSearchUsers
import ir.kt.github.domain.UseCase
import ir.kt.github.util.ApiResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val useCase: UseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _isFavorite = mutableStateOf(false)
    val isFavorite: State<Boolean> = _isFavorite

    private val _stateDetail = mutableStateOf(StateDetail())
    val stateDetail: State<StateDetail> = _stateDetail

    private val _countRepos = mutableStateOf(0)
    val countRepos: State<Int> = _countRepos

    val followersList = mutableStateListOf<ResponseSearchUsers.ItemUser>()
    val followingList = mutableStateListOf<ResponseSearchUsers.ItemUser>()

    private var userName = ""

    init {
        savedStateHandle.get<String>("username").let {
            it?.let { it1 ->
                getDetail(it1)
                userName = it1
                getFollowers(it1)
                getFollowing(it1)
                countRepos(it1)

            }

        }


    }

    private fun getDetail(userName: String) {
        useCase.selectUserUseCase(userName).onEach {
            when (it) {
                is ApiResource.Success -> {
                    _stateDetail.value = StateDetail(detail = it.data)
                    checkInFavorite(it.data!!.id)

                }

                is ApiResource.Loading -> {
                    _stateDetail.value = StateDetail(isLoading = true)
                }

                is ApiResource.Error -> {
                    _stateDetail.value = StateDetail(error = it.message.toString())


                }
            }
        }.launchIn(viewModelScope)
    }


    private fun getFollowers(userName: String) {
        useCase.followersUserUseCase(userName).onEach {
            when (it) {
                is ApiResource.Success -> {
                    it.data?.let { it1 -> followersList.addAll(it1) }
                }

                is ApiResource.Loading -> {
                }

                is ApiResource.Error -> {


                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getFollowing(userName: String) {
        useCase.followingUserUseCase(userName).onEach {
            when (it) {
                is ApiResource.Success -> {
                    it.data?.let { it1 -> followingList.addAll(it1) }
                }

                is ApiResource.Loading -> {
                }

                is ApiResource.Error -> {


                }
            }
        }.launchIn(viewModelScope)
    }


    fun countRepos(userName: String) {
        useCase.reposUserUseCase(userName).onEach {
            when (it) {
                is ApiResource.Success -> {
                    it.data?.let { it1 -> _countRepos.value = it1.size }
                }

                is ApiResource.Loading -> {
                }

                is ApiResource.Error -> {


                }
            }
        }.launchIn(viewModelScope)
    }

    fun insertUserInFavorite(userEntity: UserEntity) = viewModelScope.launch {
        useCase.insertUserFavoriteUseCase(userEntity)
        delay(1000)
        checkInFavorite(userEntity.id)

    }

    private fun checkInFavorite(id: Int) = viewModelScope.launch {
        _isFavorite.value = useCase.checkUserFavoriteUseCase(id)

    }

    fun removeUserInFavorite(id: Int) = viewModelScope.launch {
        useCase.removeUserFavoriteUseCase(id)
        delay(1000)
        checkInFavorite(id)
    }
}