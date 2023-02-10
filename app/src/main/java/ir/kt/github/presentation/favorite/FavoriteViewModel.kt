package ir.kt.github.presentation.favorite

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.kt.github.data.loacl.UserEntity
import ir.kt.github.domain.UseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val useCase: UseCase) : ViewModel() {

     val favoriteList = mutableStateListOf<UserEntity>()

    init {
        getAllFavorite()
    }

    fun getAllFavorite() = viewModelScope.launch {
      favoriteList.addAll(useCase.getAllFavoriteUseCase())
    }
}