package shpp.maslak.task3.data.dataSource


import android.util.Log
import shpp.maslak.task3.data.source.ContactApi
import shpp.maslak.task3.data.source.requests.AddContactRequest
import shpp.maslak.task3.data.source.requests.NewUserRequest
import shpp.maslak.task3.data.source.responses.GetAllUsersResponseBody
import shpp.maslak.task3.data.source.responses.GetUserContactsResponseBody
import shpp.maslak.task3.data.source.responses.UserResponseBody
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val contactApi: ContactApi,
) {

    suspend fun authorizeUser(email: String, password: String): UserResponseBody? {

        return try {
            val request = NewUserRequest(
                email,
                password
            )
            val serverResponse = contactApi.authorizeUser(request)

            if (serverResponse.isSuccessful && serverResponse.body() != null) {
                serverResponse.body()?.data

            } else {

                null
            }

        } catch (ex: Exception) {

            null
        }
        // todo handle the internet exception

    }

    suspend fun createNewUser(email: String, password: String): UserResponseBody? {

        return try {

            val request = NewUserRequest(
                email,
                password
            )
            val serverResponse = contactApi.createNewUser(request)

            if (serverResponse.isSuccessful && serverResponse.body() != null) {

                serverResponse.body()?.data


            } else {

                null
            }

        } catch (ex: Exception) {

            null
        }
        // todo handle the internet exception


    }

    suspend fun getUser(userId: Int, accessToken: String): UserResponseBody? {

        return try {

            val serverResponse = contactApi.getUser("Bearer $accessToken", userId)

            if (serverResponse.isSuccessful && serverResponse.body() != null) {
                serverResponse.body()?.data

            } else {

                null
            }

        } catch (ex: Exception) {

            null
        }
        // todo handle the internet exception

    }

    suspend fun getAllUsers( accessToken: String): GetAllUsersResponseBody? {

        return try {

            val serverResponse = contactApi.getAllUsers("Bearer $accessToken")

            if (serverResponse.isSuccessful && serverResponse.body() != null) {
                serverResponse.body()?.data

            } else {

                null
            }

        } catch (ex: Exception) {

            null
        }
        // todo handle the internet exception

    }

    suspend fun getUserContacts(userId: Int, accessToken: String): GetUserContactsResponseBody? {

        return try {

            val serverResponse = contactApi.getUserContacts(userId,"Bearer $accessToken")

            if (serverResponse.isSuccessful && serverResponse.body() != null) {
                serverResponse.body()?.data

            } else {

                null
            }

        } catch (ex: Exception) {

            null
        }
        // todo handle the internet exception

    }

    suspend fun addContact(userId: Int,contactId: Int,  accessToken: String): GetUserContactsResponseBody? {

        val request = AddContactRequest(
           contactId)
        return try {

            val serverResponse = contactApi.addContact( "Bearer $accessToken",userId, request)

            if (serverResponse.isSuccessful && serverResponse.body() != null) {
                Log.d("myLog", "response ${serverResponse.body()?.data}")
                serverResponse.body()?.data


            } else {

                null
            }

        } catch (ex: Exception) {

            null
        }
        // todo handle the internet exception

    }

    suspend fun deleteContact(userId: Int,contactId: Int,  accessToken: String): GetUserContactsResponseBody? {

        val request = AddContactRequest(
            contactId)
        return try {

            val serverResponse = contactApi.deleteContact( "Bearer $accessToken",userId, contactId)

            if (serverResponse.isSuccessful && serverResponse.body() != null) {
                Log.d("myLog", "response ${serverResponse.body()?.data}")
                serverResponse.body()?.data


            } else {

                null
            }

        } catch (ex: Exception) {

            null
        }
        // todo handle the internet exception

    }

}