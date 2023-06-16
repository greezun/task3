package shpp.maslak.task3.ui.fragments.main.addContacts


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import shpp.maslak.task3.data.LoginDataStore
import shpp.maslak.task3.data.model.User
import shpp.maslak.task3.domain.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class AddContactsViewModel @Inject constructor(

    private val dataStore: LoginDataStore,
    private val userRepository: UserRepository
) : ViewModel() {

    private lateinit var accessToken: String
    private var userId = 0

    private var _contactList = MutableStateFlow<List<User>>(emptyList())
    val contactState = _contactList

    init {
        viewModelScope.launch {
            accessToken = async { getAccessToken() }.await()
            userId = async { getUserId() }.await()
            getAllUsers(accessToken)
        }
    }

    private suspend fun getUserId(): Int {
        return dataStore.userIdFlow.first()
    }


    private fun getAllUsers(accessToken: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = userRepository.getAllUsers(accessToken)
            if (response != null) {
                val allUsersList = response.users
                _contactList.value = allUsersList

            }
        }
    }

    private suspend fun getAccessToken(): String {
        return dataStore.accessTokenFlow.first()
    }


    fun addContact(contact: User) {
        viewModelScope.launch(Dispatchers.IO) {
           userRepository.addContact(userId, contact.id,accessToken )
//            if (response != null) {
//                val contactList = response.contacts
//                _contactList.value = contactList

//            }
        }
    }

}