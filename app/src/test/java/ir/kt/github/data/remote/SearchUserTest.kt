package ir.kt.github.data.remote

import ir.kt.github.data.remote.dto.ResponseSearchUsers
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito


class SearchUserTest {

    private lateinit var userApi: UserApi
    private lateinit var users: List<ResponseSearchUsers.ItemUser>

    @Before
    fun setup() {
        userApi = FakeUserApi()
        users = listOf(
            ResponseSearchUsers.ItemUser(
                "", "", "", "", "", "", "", 1, "sam", "", "", "", "", 1, false, "", "", "", ""
            ),

            ResponseSearchUsers.ItemUser(
                "", "", "", "", "", "", "", 1, "inter", "", "", "", "", 1, false, "", "", "", ""
            )
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Search users With existing user name` = runTest {
        val searchUserName = userApi.searchUsers("sam")
        Assert.assertEquals(users, searchUserName)

    }

//    @Test
//    fun getAllMoviesTest() {
//        runBlocking {
//            Mockito.`when`(mainRepository.getAllMovies())
//                .thenReturn(Response.success(listOf<Movie>(Movie("movie", "", "new"))))
//
//            val result = userApi.searchUsers("sam")
//            assertEquals(listOf<ResponseSearchUsers>(), result)
//        }
//    }
}