package shpp.maslak.task3.ui.fragments.auth.signIn


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHost
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import shpp.maslak.task3.data.LoginDataStore
import shpp.maslak.task3.domain.repository.UserRepository
import shpp.maslak.task3.ui.navigator.NavigationEvents
import shpp.maslak.task3.util.Event
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val dataStore: LoginDataStore,



) : ViewModel() {



    private val _events = MutableStateFlow<NavigationEvents?>(null)
    val events = _events.asStateFlow()

    private var _isUserAuthorized = MutableStateFlow(false)
    val isUserAuthorized = _isUserAuthorized.asStateFlow()


    fun authoriseUser(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = userRepository.authorizeUser(email, password)
            if (response != null) {
                val accessToken = response.accessToken
                val refreshToken = response.refreshToken
                val user = response.user
                dataStore.storeTokens(accessToken, refreshToken)
                setEvent(NavigationEvents.ToNextFragment(user))



            }
        }
    }


    private fun setEvent(event: NavigationEvents){
        _events.value = event
    }
}