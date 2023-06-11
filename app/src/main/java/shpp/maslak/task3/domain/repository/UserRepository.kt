package shpp.maslak.task3.domain.repository

import shpp.maslak.task3.data.model.User
import shpp.maslak.task3.data.source.responses.UserResponseBody

interface UserRepository {

    suspend fun authorizeUser(email: String, password: String): UserResponseBody?

    suspend fun createNewUser(email: String, password: String): UserResponseBody?

    suspend fun getUser(userId:Long, accessToken:String): User?
}