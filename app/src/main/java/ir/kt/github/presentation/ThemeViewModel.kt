package ir.kt.github.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.kt.github.data.repository.StoreDataRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(private val dataStore: StoreDataRepository):ViewModel() {

    private val _isDark = mutableStateOf(false)
    val isDark : State<Boolean> = _isDark

    init {
        viewModelScope.launch {
            dataStore.getIsDark.collectLatest {
                _isDark.value = it
            }
        }
    }

    fun toggleLightTheme() =viewModelScope.launch{
        _isDark.value = !_isDark.value
        delay(1000)
        dataStore.saveIsDark(_isDark.value)

    }

}