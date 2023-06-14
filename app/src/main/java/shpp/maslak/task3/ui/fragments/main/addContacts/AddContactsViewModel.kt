package shpp.maslak.task3.ui.fragments.main.addContacts


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import shpp.maslak.task3.data.LoginDataStore
import shpp.maslak.task3.data.model.Contact
import shpp.maslak.task3.data.model.User
import shpp.maslak.task3.domain.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class AddContactsViewModel @Inject constructor(

    private val dataStore: LoginDataStore,
    private val userRepository: UserRepository
) : ViewModel() {


    private var _contactList = MutableStateFlow<List<User>>(emptyList())
    val contactState = _contactList

    private var _selectedContactsList = MutableStateFlow<List<Contact>>(emptyList())
    val  multiselectMode: StateFlow<List<Contact>> = _selectedContactsList

    init {
        viewModelScope.launch {
            val  accessToken = async {  getAccessToken()}.await()

            getAllUsers(accessToken)
        }

    }


    private fun getAllUsers(accessToken: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = userRepository.getAllUsers(accessToken)
            if (response != null) {
                val allUsersList = response.users
               _contactList.value = allUsersList
                Log.d("myTag", "users list ${allUsersList.toString()}")

            }
        }
    }

    private suspend fun getAccessToken(): String{
        return dataStore.accessTokenFlow.first()
    }



}