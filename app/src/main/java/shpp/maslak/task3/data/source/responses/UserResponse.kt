package shpp.maslak.task3.data.source.responses

import shpp.maslak.task3.data.model.User

data class UserResponse (
        val code: Int,
        val `data`: UserResponseBody,
        val message: String,
        val status: String
        )


data class UserResponseBody(
        val accessToken: String,
        val refreshToken: String,
        val user: User
)