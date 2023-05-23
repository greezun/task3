package shpp.maslak.task3.data.model


import android.os.Parcel
import android.os.Parcelable
import java.io.File
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class User(
    val id:Int,
    val name: String?,
    val phone: String?,
    val address: String?,
    val career: String?,
    val birthday:String?,
    val facebook: String?,
    val instagram: String?,
    val twitter: String?,
    val linkedin: String?,
    val image: String?
): Parcelable


