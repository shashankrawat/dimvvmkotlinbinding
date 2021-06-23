package com.dimvvmkotlinbinding.di

import com.dimvvmkotlinbinding.common.dialogs.InfoDialog
import com.dimvvmkotlinbinding.common.dialogs.ProgressBarDialog
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DialogsModule {

    @Provides
    fun provideInfoDialog() : InfoDialog = InfoDialog()

    @Provides
    fun provideProgressDialog() : ProgressBarDialog = ProgressBarDialog()
}