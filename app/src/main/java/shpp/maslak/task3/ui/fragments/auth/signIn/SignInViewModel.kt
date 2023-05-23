package shpp.maslak.task3.ui.fragments.auth.signIn

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import shpp.maslak.task3.data.LoginDataStore
import shpp.maslak.task3.domain.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val dataStore: LoginDataStore

) : ViewModel() {



    fun createNewUser(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = userRepository.createNewUser(email, password)
            if(response != null){
                val accessToken = response.accessToken
                val refreshToken = response.accessToken
                val user = response.user

            dataStore.storeTokens(accessToken, refreshToken)
            }






            Log.d("MyProfileViewModel", "response: $response")
        }
    }
}