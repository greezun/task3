package shpp.maslak.task3.data.source


import retrofit2.http.*
import shpp.maslak.task3.data.model.User
import shpp.maslak.task3.data.source.requests.AddContactRequest
import shpp.maslak.task3.data.source.requests.AuthorizeUserRequest
import shpp.maslak.task3.data.source.requests.EditUserRequest
import shpp.maslak.task3.data.source.responses.AddContactResponse
import shpp.maslak.task3.data.source.responses.UserResponse
import shpp.maslak.task3.data.source.responses.GetAllUsersResponse
import shpp.maslak.task3.data.source.responses.GetUserContactsResponse
import shpp.maslak.task3.data.source.responses.GetUserResponse
import shpp.maslak.task3.data.source.responses.RefreshTokenResponse


interface ContactApi {
    @POST("users")
    suspend fun createNewUser(@Body body: User): UserResponse

    @POST("login")
    suspend fun logIn(@Body body: AuthorizeUserRequest): UserResponse

    @POST("refresh")
    suspend fun refreshToken(@Header("RefreshToken") accessToken: String): RefreshTokenResponse

    @GET("users/{userId}")
    suspend fun getUser(@Header("Authorization") accessToken: String): GetUserResponse

    @PUT("users/{userId}")
    suspend fun editUser(
        @Header("Authorization") accessToken: String,
        @Path("userId") userId: Int,
        @Body body: EditUserRequest
    ): GetUserResponse


    @GET("users")
    suspend fun getAllUsers(@Header("Authorization") accessToken: String): GetAllUsersResponse


    @PUT("users/{contactId}/contacts")
    suspend fun addContact(
        @Path("contactID") contactId: Int,
        @Body body: AddContactRequest
    ): AddContactResponse

    @DELETE("users/{userId}/contacts/{contactId}")
    suspend fun deleteContact(
        @Header("Authorization") accessToken: String,
        @Path("userId") userId: Int,
        @Path("contactId") contactId: Int
    ):GetUserContactsResponse

    @GET("users/{contactId}/contacts")
    suspend fun getUserContacts(
        @Path("contactId") contactId: Int,
        @Header("Authorization") accessToken: String
    ): GetUserContactsResponse

}