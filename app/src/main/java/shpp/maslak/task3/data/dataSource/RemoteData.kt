package shpp.maslak.task3.data.dataSource

import android.util.Log
import shpp.maslak.task3.data.source.ContactApi
import shpp.maslak.task3.data.source.requests.NewUserRequest
import shpp.maslak.task3.data.source.responses.UserResponseBody
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val contactApi: ContactApi,
) {

    suspend fun authorizeUser(email: String, password: String): UserResponseBody? {

        return try {
            Log.d("RemoteData", "email: $email")
            Log.d("RemoteData", "password: $password")
            val request = NewUserRequest(
                email,
                password
            )
            val serverResponse = contactApi.authorizeUser(request)

            if (serverResponse.isSuccessful && serverResponse.body() != null) {
                Log.d("RemoteData", "serverResponse.body(): ${serverResponse.body()}")
                serverResponse.body()?.data


            } else {
                Log.d("RemoteData", "serverResponse.isSuccessful: ${serverResponse.isSuccessful}")
                Log.d("RemoteData", "serverResponse.body(): ${serverResponse.body()}")

                null//                UserResponse()
            }

        } catch (ex: Exception) {
            Log.d("RemoteData", "ex: ${ex.message}")
            null
        }
        // todo handle the internet exception
//        try {
//
//        } catch (ex: java.lang.Exception) {
//
//        } catch (ex: NetworkException) {
//
//        }

    }

    suspend fun createNewUser(email: String, password: String): UserResponseBody? {

        return try {
            Log.d("RemoteData", "email: $email")
            Log.d("RemoteData", "password: $password")
            val request = NewUserRequest(
                email,
                password
            )
            val serverResponse = contactApi.createNewUser(request)

            if (serverResponse.isSuccessful && serverResponse.body() != null) {
                Log.d("RemoteData", "serverResponse.body(): ${serverResponse.body()}")
                serverResponse.body()?.data


            } else {
                Log.d("RemoteData", "serverResponse.isSuccessful: ${serverResponse.isSuccessful}")
                Log.d("RemoteData", "serverResponse.body(): ${serverResponse.body()}")

                null//                UserResponse()
            }

        } catch (ex: Exception) {
            Log.d("RemoteData", "ex: ${ex.message}")
            null
        }
        // todo handle the internet exception
//        try {
//
//        } catch (ex: java.lang.Exception) {
//
//        } catch (ex: NetworkException) {
//
//        }

    }

}