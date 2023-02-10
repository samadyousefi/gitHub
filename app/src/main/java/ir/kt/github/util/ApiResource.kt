package ir.kt.github.util

sealed class ApiResource<T>(val data: T? = null, val message: String? = null) {
    class Loading<T>(data: T? = null) : ApiResource<T>(data)
    class Success<T>(data: T) : ApiResource<T>(data)
    class Error<T>(message: String, data: T? = null) : ApiResource<T>(data, message)
}


