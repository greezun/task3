package shpp.maslak.task3.data.model

import java.io.File
import java.util.Date

data class User(
    val id:Int,
    val name: String?,
    val phone: String?,
    val address: String?,
    val career: String?,
    val birthday: Date?,
    val facebook: String?,
    val instagram: String?,
    val twitter: String?,
    val linkedin: String?,
    val image: File?
)
