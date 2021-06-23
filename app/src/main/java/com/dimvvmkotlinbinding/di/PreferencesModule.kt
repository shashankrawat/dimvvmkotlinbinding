package com.dimvvmkotlinbinding.di

import com.dimvvmkotlinbinding.common.helpers.PreferencesHelper
import com.dimvvmkotlinbinding.preferences.UserSession
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PreferencesModule
{
    @Provides
    fun provideGson() = Gson()

    @Singleton
    @Provides
    fun providesUserSession(preferencesHelper: PreferencesHelper, gson: Gson): UserSession {
        return UserSession(preferencesHelper, gson)
    }
}