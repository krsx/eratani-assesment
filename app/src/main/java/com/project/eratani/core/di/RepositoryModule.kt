package com.project.eratani.core.di

import com.project.eratani.core.data.repository.ApiCallRepository
import com.project.eratani.core.domain.repository.IApiCallRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideApiCallRepository(apiCallRepository: ApiCallRepository): IApiCallRepository


}