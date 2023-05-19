package shpp.maslak.task3.domain.repository

import shpp.maslak.task3.data.source.responses.UserResponseBody

interface UserRepository {

    suspend fun authorizeUser(email: String, password: String): UserResponseBody?
}