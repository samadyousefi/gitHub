package ir.kt.github.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StoreDataRepository(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("user-")

        val IsDark = booleanPreferencesKey("theme")



    }




    val getIsDark: Flow<Boolean> = context.dataStore.data.map {
        it[IsDark] ?: false
    }

    suspend fun saveIsDark(state: Boolean) {
        context.dataStore.edit {
            it[IsDark] = state
        }
    }


}