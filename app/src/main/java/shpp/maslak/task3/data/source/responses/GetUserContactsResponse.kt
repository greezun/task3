package shpp.maslak.task3.data.source.responses

import shpp.maslak.task3.data.model.Contact



data class GetUserContactsResponse (
    val code: Int,
    val `data`: GetUserContactsResponseBody,
    val message: String,
    val status: String
        )

data class GetUserContactsResponseBody (
val contacts: List<Contact>
)