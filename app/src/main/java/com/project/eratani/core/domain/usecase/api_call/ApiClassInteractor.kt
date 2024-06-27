package com.project.eratani.core.domain.usecase.api_call

import com.project.eratani.core.data.repository.ApiCallRepository
import com.project.eratani.core.data.source.Resource
import com.project.eratani.core.domain.model.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ApiClassInteractor @Inject constructor(private val apiCallRepository: ApiCallRepository):
    ApiCallUseCase {
    override fun registerUser(
        name: String,
        email: String,
        gender: String,
        status: String
    ): Flow<Resource<User>> {
        return apiCallRepository.registerUser(name, email, gender, status)
    }

    override fun getUsers(): Flow<Resource<List<User>>> {
        return apiCallRepository.getUsers()
    }
}