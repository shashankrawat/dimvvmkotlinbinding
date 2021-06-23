package com.dimvvmkotlinbinding.di

import com.dimvvmkotlinbinding.common.adapters.DropdownAdapter
import com.dimvvmkotlinbinding.ui.dashboard.adapters.DashboardAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AdaptersModule {

    @Provides
    fun provideLoginAdapter(): DashboardAdapter = DashboardAdapter()

    @Provides
    fun provideDropdownAdapter(): DropdownAdapter = DropdownAdapter()
    
}