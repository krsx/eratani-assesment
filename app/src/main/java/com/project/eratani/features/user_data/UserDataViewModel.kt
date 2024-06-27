package com.project.eratani.features.user_data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.project.eratani.core.domain.usecase.api_call.ApiCallUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserDataViewModel @Inject constructor(private val apiCallUseCase: ApiCallUseCase) : ViewModel() {

    fun getUsers() = apiCallUseCase.getUsers().asLiveData()
}