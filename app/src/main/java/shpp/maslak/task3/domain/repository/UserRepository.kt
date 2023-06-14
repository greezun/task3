package shpp.maslak.task3.domain.repository


import shpp.maslak.task3.data.source.responses.GetAllUsersResponseBody
import shpp.maslak.task3.data.source.responses.UserResponseBody

interface UserRepository {

    suspend fun authorizeUser(email: String, password: String): UserResponseBody?

    suspend fun createNewUser(email: String, password: String): UserResponseBody?

    suspend fun getUser(userId:Int, accessToken:String): UserResponseBody?

    suspend fun getAllUsers(accessToken:String): GetAllUsersResponseBody?
}