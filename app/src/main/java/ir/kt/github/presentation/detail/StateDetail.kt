package ir.kt.github.presentation.detail

import ir.kt.github.data.remote.dto.ResponseSelectUser

data class StateDetail(val isLoading:Boolean = false , val detail:ResponseSelectUser? = null , val error:String = "")
