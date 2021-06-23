package com.dimvvmkotlinbinding.di

import com.dimvvmkotlinbinding.api.managers.LoginApiManager
import com.dimvvmkotlinbinding.common.helpers.NetworkHelper
import com.dimvvmkotlinbinding.ui.login.repo.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideUserLoginRepository(
        loginApiManager: LoginApiManager,
        networkHelper: NetworkHelper
    ): LoginRepository {
        return LoginRepository(loginApiManager, networkHelper)
    }

}