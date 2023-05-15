package shpp.maslak.task3.data.source.responses

import shpp.maslak.task3.data.model.User

data class GetAllUsersResponse(
    val code: Int,
    val `data`: GetAllUsersResponseBody,
    val message: String,
    val status: String
)

data class GetAllUsersResponseBody(
    val users: List<User>
)