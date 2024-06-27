package com.project.eratani.core.data.repository

import com.project.eratani.core.data.source.NetworkBoundResource
import com.project.eratani.core.data.source.Resource
import com.project.eratani.core.data.source.remote.data_source.RemoteDataSource
import com.project.eratani.core.data.source.remote.network.ApiResponse
import com.project.eratani.core.data.source.remote.response.UserResponse
import com.project.eratani.core.domain.model.User
import com.project.eratani.core.domain.repository.IApiCallRepository
import com.project.eratani.core.utils.data_mapper.UserDataMapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiCallRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): IApiCallRepository {
    override fun registerUser(name: String, email: String, gender: String, status: String,): Flow<Resource<User>> {
        return object: NetworkBoundResource<User, UserResponse>(){
            override suspend fun fetchFromApi(response: UserResponse): User {
                return UserDataMapper.mapUserReponseToDomain(response)
            }

            override suspend fun createCall(): Flow<ApiResponse<UserResponse>> {
                return remoteDataSource.registerUser(name, email, gender, status)
            }
        }.asFlow()
    }

    override fun getUsers(): Flow<Resource<List<User>>> {
        return object : NetworkBoundResource<List<User>, List<UserResponse>>(){
            override suspend fun fetchFromApi(response: List<UserResponse>): List<User> {
                return UserDataMapper.mapListUserReponseToDomain(response)
            }

            override suspend fun createCall(): Flow<ApiResponse<List<UserResponse>>> {
                return remoteDataSource.getUsers()
            }

        }.asFlow()
    }
}