package shpp.maslak.task3.data.repository

import shpp.maslak.task3.data.dataSource.RemoteDataSource
import shpp.maslak.task3.data.source.responses.GetAllUsersResponseBody
import shpp.maslak.task3.data.source.responses.GetUserContactsResponseBody
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

    override suspend fun getUser(userId: Int, accessToken: String): UserResponseBody? {
        return remoteDataSource.getUser(userId, accessToken )
    }

    override suspend fun getAllUsers(accessToken: String): GetAllUsersResponseBody? {
        return remoteDataSource.getAllUsers(accessToken)
    }

    override suspend fun getUserContacts(
        userId: Int,
        accessToken: String
    ): GetUserContactsResponseBody? {
        return  remoteDataSource.getUserContacts(userId, accessToken)
    }

    override suspend fun addContact(
        userId: Int,
        contactId: Int,
        accessToken: String
    ): GetUserContactsResponseBody? {
        return remoteDataSource.addContact(userId, contactId, accessToken)
    }

    override suspend fun deleteContact(
        userId: Int,
        contactId: Int,
        accessToken: String
    ): GetUserContactsResponseBody? {
       return  remoteDataSource.deleteContact(userId, contactId, accessToken)
    }
}