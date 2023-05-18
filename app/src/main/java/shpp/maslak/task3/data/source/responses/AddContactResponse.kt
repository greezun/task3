package shpp.maslak.task3.data.source.responses

import shpp.maslak.task3.data.model.User


data class AddContactResponse(
    val code: Int,
    val `data`: AddContactResponseBody,
    val message: String,
    val status: String
)

data class AddContactResponseBody(
    val user: User
)
