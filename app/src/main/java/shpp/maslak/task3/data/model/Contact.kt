package shpp.maslak.task3.data.model

import android.net.Uri

data class Contact(
    val id: Long,
    val avatar: Uri?,
    val userName: String,
    val address: String,
    val career:String,
    var isSelected: Boolean = false)
