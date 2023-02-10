package ir.kt.github.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ir.kt.github.App
import ir.kt.github.util.Constants
import ir.kt.github.data.loacl.UsersDatabase
import ir.kt.github.data.remote.UserApi
import ir.kt.github.data.repository.StoreDataRepository
import ir.kt.github.data.repository.UserRepository
import ir.kt.github.domain.UseCase
import ir.kt.github.domain.use_case.check_fav.CheckUserFavoriteUseCase
import ir.kt.github.domain.repository.UserRepositoryImpl
import ir.kt.github.domain.use_case.fav_list.GetAllFavoriteUseCase
import ir.kt.github.domain.use_case.followers.FollowersUserUseCase
import ir.kt.github.domain.use_case.following.FollowingUserUseCase
import ir.kt.github.domain.use_case.remove_fav.RemoveUserFavoriteUseCase
import ir.kt.github.domain.use_case.repos.ReposUserUseCase
import ir.kt.github.domain.use_case.search_users.SearchUsersUseCase
import ir.kt.github.domain.use_case.select.SelectUserUseCase
import ir.kt.github.domain.use_case.user_fav.InsertUserFavoriteUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun providerApplication(@ApplicationContext app:Context):App {
        return app as App
    }

    @Provides
    @Singleton
    fun provideDataStore(application: Application): StoreDataRepository {
        return StoreDataRepository(application)
    }

    @Provides
    @Singleton
    fun provideUserDatabase(application: Application): UsersDatabase {
        return Room.databaseBuilder(
            application, UsersDatabase::class.java,
            UsersDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserRepository(api: UserApi, db: UsersDatabase): UserRepository {
        return UserRepositoryImpl(api, db.dao)
    }

    @Provides
    @Singleton
    fun provideUserApi(): UserApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUseCase(repository: UserRepository): UseCase {
        return UseCase(
            insertUserFavoriteUseCase = InsertUserFavoriteUseCase(repository),
            searchUsersUseCase = SearchUsersUseCase(repository),
            selectUserUseCase = SelectUserUseCase(repository),
            followersUserUseCase = FollowersUserUseCase(repository),
            followingUserUseCase = FollowingUserUseCase(repository),
            checkUserFavoriteUseCase = CheckUserFavoriteUseCase(repository),
            removeUserFavoriteUseCase = RemoveUserFavoriteUseCase(repository),
            getAllFavoriteUseCase = GetAllFavoriteUseCase(repository),
            reposUserUseCase = ReposUserUseCase(repository)
        )
    }
}