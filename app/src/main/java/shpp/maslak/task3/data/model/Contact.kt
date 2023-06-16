package shpp.maslak.task3.data.model

import android.net.Uri

data class Contact(
    val id: Int,
    val email: String,
    val avatar: Uri?,
    val userName: String,
    val address: String,
    val career:String,
    var isSelected: Boolean = false)
