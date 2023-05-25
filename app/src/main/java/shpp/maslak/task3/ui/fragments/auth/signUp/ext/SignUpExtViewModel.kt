package shpp.maslak.task3.ui.fragments.auth.signUp.ext

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import shpp.maslak.task3.data.model.User
import javax.inject.Inject

@HiltViewModel
class SignUpExtViewModel @Inject constructor():ViewModel() {


    private var _currentUser = MutableStateFlow<User?>(null)
    val currentUser = _currentUser.asStateFlow()

    private var _currentUserPhotoUri = MutableStateFlow<Uri?>(null)
    val currentUserPhotoUri = _currentUserPhotoUri.asStateFlow()

    private var _currentUserName = MutableStateFlow("")
    val currentUserName = _currentUserName.asStateFlow()

    private var _currentUserPhone = MutableStateFlow("")
    val currentUserPhone = _currentUserPhone.asStateFlow()

    fun initUser(currentUser: User) {
        _currentUser.value = currentUser
    }

    fun setUsername(name: String) {
        _currentUser.value?.name = name
    }


    fun senPhoneNumber(number: String){
        _currentUser.value?.phone = number
    }

    fun setPhoto(uri:Uri){
       _currentUserPhotoUri.value = uri
        Log.d("uri", "uri $uri")
    }

}