package shpp.maslak.task3.data.source.responses

import shpp.maslak.task3.data.model.User


data class GetUserResponse(
    val code: Int,
    val `data`: GetUserResponseBody,
    val message: String,
    val status: String
)

data class GetUserResponseBody(
    val user: User
)