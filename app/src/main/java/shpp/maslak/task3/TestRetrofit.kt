package shpp.maslak.task3


import com.squareup.moshi.Moshi
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
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
    @POST("login")
    @Headers("Content-type: application/json")
    suspend fun signUp(@Body body: SignUpRequestBody): SignUpResponseBody
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

    val response = api.signUp(requestBody)

    println(response)
}

object ServerApi {
    private val loggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)
    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()
    private val moshi: Moshi = Moshi.Builder()

        .build()
//    val moshiConverterFactory = MoshiConverterFactory.create(moshi)

    val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(UsersApi::class.java)

    val requestBody = SignUpRequestBody(
        email = "test5@email",
        password = "123"
    )

//    val response = api.signUp(requestBody)
}