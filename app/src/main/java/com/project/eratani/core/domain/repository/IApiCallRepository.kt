package com.project.eratani.core.domain.repository

import com.project.eratani.core.data.source.Resource
import com.project.eratani.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface IApiCallRepository {
    fun registerUser(
        name: String,
        email: String,
        gender: String,
        status: String,
    ): Flow<Resource<User>>

    fun getUsers(): Flow<Resource<List<User>>>
}