package ir.kt.github.presentation.home

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.kt.github.domain.UseCase
import ir.kt.github.util.ApiResource
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val useCase: UseCase) : ViewModel() {

    private val _currentPage = mutableStateOf(1)
    val currentPage = _currentPage

    private val _stateSearch = mutableStateOf(SearchState())
    val stateSearch: State<SearchState> = _stateSearch

    private val _nameSearch = mutableStateOf("")
    val nameSearch:State<String> = _nameSearch

    fun search(name:String){
        _nameSearch.value = name
    }

//    fun getList(name:String): Flow<PagingData<ResponseSearchUsers.Item>> {
//        val data = Pager(PagingConfig(20)){
//            SearchPodCastDataSource(useCase,name = name , perPage = 30, currentPage = _currentPage.value)
//        }.flow.cachedIn(viewModelScope)
//        return data
//    }

    fun searchUser(name:String)  {
        useCase.searchUsersUseCase(name).onEach {
            when(it){
                is ApiResource.Success ->{

                    _stateSearch.value = SearchState(list = it.data?.itemUsers ?: emptyList())
                    Log.d("Success" , it.data?.itemUsers.toString())
                }
                is ApiResource.Loading ->{
                    _stateSearch.value = SearchState(loading = true)
                }
                is ApiResource.Error -> {
                    Log.d("Success" , it.message.toString())

                    _stateSearch.value = SearchState(error = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }
}