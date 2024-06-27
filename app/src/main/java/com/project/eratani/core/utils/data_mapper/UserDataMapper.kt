package com.project.eratani.core.utils.data_mapper

import com.project.eratani.core.data.source.remote.response.UserResponse
import com.project.eratani.core.domain.model.User

object UserDataMapper {

    fun mapUserReponseToDomain(input: UserResponse): User = User(
        id = input.id!!,
        name = input.name ?: "name",
        email = input.email ?: "email",
        gender = input.gender ?: "gender",
        status = input.status ?: "status",
    )

    fun mapListUserReponseToDomain(input: List<UserResponse>): List<User> = input.map { mapUserReponseToDomain(it) }

}