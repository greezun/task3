package shpp.maslak.task3.ui.fragments.main.myContacts


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import shpp.maslak.task3.data.ContactManager
import shpp.maslak.task3.data.LoginDataStore
import shpp.maslak.task3.data.model.Contact
import shpp.maslak.task3.data.model.User
import shpp.maslak.task3.domain.repository.UserRepository
import shpp.maslak.task3.ui.navigator.NavigationEvents
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(
    private val manager: ContactManager,
    private val dataStore: LoginDataStore,
    private val userRepository: UserRepository
) : ViewModel() {


    private var _contactList = MutableStateFlow<List<Contact>>(emptyList())
    val contactState = _contactList

    private var _selectedContactsList = MutableStateFlow<List<Contact>>(emptyList())
    val  multiselectMode: StateFlow<List<Contact>> = _selectedContactsList

    init {
        viewModelScope.launch {
//            val  accessToken = async {  getAccessToken()}.await()
//
//            getAllUsers(accessToken)
        }

    }

    fun addContactOnIndex(index: Int, contact: Contact) {
        manager.addContactFromIndex(index, contact)

    }

    fun getContact(index: Int) = manager.getContact(index)
    fun deleteContact(contact: Contact){
        return manager.deleteContact(contact)
    }

    fun getIndex(contact: Contact): Int = manager.getIndex(contact)

    fun changeSelectedList(contact: Contact){
        _selectedContactsList.value = _selectedContactsList.value.toMutableList().apply {
            if(contact.isSelected){
                add(contact)
            } else{
                remove(contact)
            }

        }
    }
    fun deleteSelected() {
        _contactList.value = _contactList.value.toMutableList().filter { !it.isSelected }
        _selectedContactsList.value = emptyList()
    }


//    fun getAllUsers(accessToken: String) {
//        viewModelScope.launch(Dispatchers.IO) {
//            val response = userRepository.getAllUsers(accessToken)
//            if (response != null) {
//                val allUsersList = response.users
//               _contactList.value = allUsersList
//
//            }
//        }
//    }

//    private suspend fun getAccessToken(): String{
//        return dataStore.accessTokenFlow.first()
//    }



}