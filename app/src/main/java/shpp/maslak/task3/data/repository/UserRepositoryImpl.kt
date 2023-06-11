package shpp.maslak.task3.data.repository

import shpp.maslak.task3.data.dataSource.RemoteDataSource
import shpp.maslak.task3.data.model.User
import shpp.maslak.task3.data.source.responses.UserResponseBody
import shpp.maslak.task3.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : UserRepository {
    override suspend fun authorizeUser(email: String, password: String): UserResponseBody? {
        return remoteDataSource.authorizeUser(email, password)
    }

    override suspend fun createNewUser(email: String, password: String): UserResponseBody? {
        return remoteDataSource.createNewUser(email, password)
    }

    override suspend fun getUser(userId: Long, accessToken: String): User? {
        TODO("Not yet implemented")
    }
}