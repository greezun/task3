package shpp.maslak.task3.data.source.responses

import shpp.maslak.task3.data.model.User

data class AuthorizeUserResponse (
        val code: Int,
        val `data`: AuthorizeUserResponseBody,
        val message: String,
        val status: String
        )


data class AuthorizeUserResponseBody(
        val accessToken: String,
        val refreshToken: String,
        val user: User
)