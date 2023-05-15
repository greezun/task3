package shpp.maslak.task3.data.source


import retrofit2.http.*
import shpp.maslak.task3.data.source.requests.AuthorizeUserRequest
import shpp.maslak.task3.data.source.responses.AuthorizeUserResponse
import shpp.maslak.task3.data.source.responses.GetAllUsersResponse


interface ContactApi {

    @POST("login")
    suspend fun logIn(@Body body: AuthorizeUserRequest): AuthorizeUserResponse


    @GET("users")
    suspend fun getAllUsers(@Header("Authorization") accessToken: String): GetAllUsersResponse

}