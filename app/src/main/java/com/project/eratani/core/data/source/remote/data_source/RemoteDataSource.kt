package com.project.eratani.core.data.source.remote.data_source

import com.project.eratani.BuildConfig
import com.project.eratani.core.data.source.remote.network.ApiResponse
import com.project.eratani.core.data.source.remote.network.ApiService
import com.project.eratani.core.data.source.remote.response.UserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    private val token = "Bearer ${BuildConfig.API_TOKEN}"

    suspend fun registerUser(name: String, email: String, password: String, status: String): Flow<ApiResponse<UserResponse>> {
        return flow{
            try {
                val response  = apiService.registerUser(token,name, email, password, status)

                if (response.id != null){
                    emit(ApiResponse.Success(response))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e:Exception){
                Timber.tag("RemoteDataSource").e("registerUser: ${e.message}")
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getUsers(): Flow<ApiResponse<List<UserResponse>>>{
        return flow {
            try {
                val response = apiService.getUsers(token)

                if (response.isNotEmpty()){
                    emit(ApiResponse.Success(response))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e:Exception){
                Timber.tag("RemoteDataSource").e("getUsers: ${e.message}")
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}