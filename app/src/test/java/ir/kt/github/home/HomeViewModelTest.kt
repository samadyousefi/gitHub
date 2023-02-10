package ir.kt.github.home

import ir.kt.github.domain.UseCase
import ir.kt.github.presentation.home.HomeViewModel
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class HomeViewModelTest {

    lateinit var homeViewModel: HomeViewModel
    lateinit var useCase: UseCase



}

//@Test
//fun getAllMoviesTest() {
//    runBlocking {
//        Mockito.`when`(mainRepository.getAllMovies())
//            .thenReturn(Response.success(listOf<Movie>(Movie("movie", "", "new"))))
//        mainViewModel.getAllMovies()
//        val result = mainViewModel.movieList.getOrAwaitValue()
//        assertEquals(listOf<Movie>(Movie("movie", "", "new")), result)
//    }
//}


//fun getAllMovies() {
//
//    job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
//        loading.postValue(true)
//        val response = mainRepository.getAllMovies()
//        withContext(Dispatchers.Main) {
//            if (response.isSuccessful) {
//                movieList.postValue(response.body())
//                loading.value = false
//            } else {
//                onError("Error : ${response.message()} ")
//            }
//        }
//    }
//
//}