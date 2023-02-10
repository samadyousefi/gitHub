package ir.kt.github.presentation.home

import ir.kt.github.data.remote.dto.ResponseSearchUsers

data class SearchState(
    val loading: Boolean = false,
    val list: List<ResponseSearchUsers.ItemUser> = emptyList(),
    val error: String = ""
)
