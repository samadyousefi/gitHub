package ir.kt.github.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ir.kt.github.data.remote.dto.ResponseSearchUsers
import ir.kt.github.domain.UseCase

//class SearchPodCastDataSource(private val useCase: UseCase, private val name: String, private val perPage:Int , private val currentPage:Int) :
//    PagingSource<Int, ResponseSearchUsers.Item>() {
//    override fun getRefreshKey(state: PagingState<Int, ResponseSearchUsers.Item>): Int? {
//        return state.anchorPosition?.let { position ->
//            val page = state.closestPageToPosition(position)
//            page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
//        }
//    }
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ResponseSearchUsers.Item> {
//        return try {
//            val page = params.key ?: 1
//            val response = useCase.searchUsersUseCase(page = page, perPage = perPage,name =name )
//            LoadResult.Page(data = response.items, prevKey = null,
//                nextKey = if (response.items.isNotEmpty()) page+1 else null)
//
//        } catch (e: Exception) {
//            LoadResult.Error(e)
//        }
//    }
//}