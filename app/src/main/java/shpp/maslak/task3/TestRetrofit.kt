package shpp.maslak.task3


import com.squareup.moshi.Moshi
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query
import shpp.maslak.task3.data.model.ResponseBody
import shpp.maslak.task3.data.model.User
import shpp.maslak.task3.util.Constants

data class SignUpRequestBody(
    val email: String,
    val password: String
)

data class SignUpResponseBody(
    val user: User,
    val accessToken: String,
    val refreshToken: String
)

interface UsersApi {

    @POST("users")
    @Headers("Content-Type: multipart/form-data")
    suspend fun signUp(
        @Query("email") email: String,
        @Query("password") password: String
    ): ResponseBody
}

fun main() = runBlocking {
    println("start")
    val loggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)
    val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
    val moshi = Moshi.Builder()

        .build()
    val moshiConverterFactory = MoshiConverterFactory.create(moshi)

    val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .client(client)
        .addConverterFactory(moshiConverterFactory)
        .build()

    val api = retrofit.create(UsersApi::class.java)
    println(api)


    val requestBody = SignUpRequestBody(
        email = "test5@email",
        password = "123"
    )
    println(requestBody)

    val response = api.signUp("test8@email", "123")

    println(response)

}

