package shpp.maslak.task3.data.model


import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.File


@Parcelize
data class User(
    val id:Int,
    val email: String,
    var name: String?,
    var phone: String?,
    val address: String?,
    val career: String?,
    val birthday:String?,
    val facebook: String?,
    val instagram: String?,
    val twitter: String?,
    val linkedin: String?,
    val image: File?,
    var isSelected: Boolean = false
): Parcelable


