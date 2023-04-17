package shpp.maslak.task3.data.model
data class Contact(
    val id: Long,
    val avatar: String,
    val userName: String,
    val address: String,
    val career:String,
    var isSelected: Boolean = false)
