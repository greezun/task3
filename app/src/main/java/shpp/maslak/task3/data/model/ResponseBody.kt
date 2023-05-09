package shpp.maslak.task3.data.model

data class ResponseBody(
    val code: Int,
    val `data`: Data,
    val message: String,
    val status: String
)