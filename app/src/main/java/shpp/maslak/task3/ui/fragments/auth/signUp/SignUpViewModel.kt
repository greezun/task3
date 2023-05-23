package shpp.maslak.task3.ui.fragments.auth.signUp


import android.util.Log
import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import shpp.maslak.task3.data.LoginData
import shpp.maslak.task3.data.model.User
import shpp.maslak.task3.domain.repository.UserRepository
import shpp.maslak.task3.util.Constants
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val loginData: LoginData,
    private val userRepository: UserRepository
) : ViewModel() {

    private val coroutineScope = CoroutineScope(Job())

    private val _passwordErrorMessage = MutableStateFlow<String?>(null)
    val passwordErrorMessage: StateFlow<String?> = _passwordErrorMessage

    private val _eMailErrorMessage = MutableStateFlow<String?>(null)
    val eMailErrorMessage: StateFlow<String?> = _eMailErrorMessage

    private val _userEMail = MutableStateFlow("")
    val userEMail: StateFlow<String> = _userEMail

    private val _userPassword = MutableStateFlow("")
    val userPassword: StateFlow<String> = _userPassword

    private val _user = MutableStateFlow<User?>(null)
    val user = _user.asStateFlow()

    init {
        viewModelScope.launch {
            loginData.eMailFlow.collect {
                _userEMail.value = it
            }

        }

        viewModelScope.launch {
            loginData.passwordFlow.collect {
                _userPassword.value = it
            }
        }
    }


    fun validPassword(password: String) {

        _passwordErrorMessage.value = setPasswordErrorMessage(password)

    }

    fun validEMail(eMail: String) {
        _eMailErrorMessage.value = setEMailErrorMessage(eMail)
    }

    fun saveLoginData(eMail: String, password: String, isCheckedCheckBox: Boolean) {
        if (isCheckedCheckBox) {
            coroutineScope.launch {
                loginData.storeLoginData(eMail, password, true)

            }
        }
    }

    fun authorizeUser(email: String, password: String) {

        viewModelScope.launch(Dispatchers.IO) {
            val response = userRepository.authorizeUser(email, password)

            if (response != null) {
                val accessToken = response.accessToken
                val refreshToken = response.accessToken
                _user.value = response.user
//            dataStore.storeTokens(accessToken!!, refreshToken!!)


                Log.d("MyProfileViewModel", "response: $response")
            }
        }
    }


    private fun setEMailErrorMessage(eMail: String): String? {
        if (!Patterns.EMAIL_ADDRESS.matcher(eMail).matches()) {
            return Constants.E_MAIL_ERROR_MESSAGE
        }
        return null
    }

    private fun setPasswordErrorMessage(passwordText: String): String? {

        with(Constants) {
            if (passwordText.length < PASSWORD_MIN_SIZE) {
                return PASSWORD_SIZE_ERROR_MESSAGE
            }
            if (!passwordText.matches(REGEX_UPPER_CASE.toRegex())) {
                return PASSWORD_UPPER_CASE_ERROR_MESSAGE
            }

            if (!passwordText.matches(REGEX_LOWER_CASE.toRegex())) {
                return PASSWORD_LOWER_CASE_ERROR_MESSAGE
            }

            return null
        }
    }


}