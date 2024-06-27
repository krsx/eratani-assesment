package com.project.eratani.di

import com.project.eratani.core.domain.usecase.api_call.ApiCallUseCase
import com.project.eratani.core.domain.usecase.api_call.ApiClassInteractor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    @Binds
    @ViewModelScoped
    abstract fun provideApiCallUseCase(apiCallInteractor: ApiClassInteractor): ApiCallUseCase
}