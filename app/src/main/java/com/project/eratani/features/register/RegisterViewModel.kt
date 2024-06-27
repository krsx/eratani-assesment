package com.project.eratani.features.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.project.eratani.core.domain.usecase.api_call.ApiCallUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val apiCallUseCase: ApiCallUseCase): ViewModel() {

    fun registerUser( name: String, email: String, gender: String, status: String,) = apiCallUseCase.registerUser(name, email, gender, status).asLiveData()
}