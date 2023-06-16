package shpp.maslak.task3.ui.fragments.main.myContacts


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import shpp.maslak.task3.data.ContactManager
import shpp.maslak.task3.data.LoginDataStore
import shpp.maslak.task3.data.model.Contact
import shpp.maslak.task3.domain.repository.UserRepository

import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(
    private val manager: ContactManager,
    private val dataStore: LoginDataStore,
    private val userRepository: UserRepository
) : ViewModel() {

    private lateinit var accessToken: String
    private var userId = -1

    private var _contactList = MutableStateFlow<List<Contact>>(emptyList())
    val contactState = _contactList

    private var _selectedContactsList = MutableStateFlow<List<Contact>>(emptyList())
    val multiselectMode = _selectedContactsList.asStateFlow()

    init {
        viewModelScope.launch {
            accessToken = async { getAccessToken() }.await()
             userId = async { getUserId() }.await()
            getUserContacts()

        }

    }

    fun addContact(contact: Contact) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.addContact(userId, contact.id,accessToken )
            getUserContacts()
        }
    }

    fun getContact(index: Int) = _contactList.value[index]
    fun deleteContact(contact: Contact) {
        viewModelScope.launch {
            userRepository.deleteContact(userId, contact.id, accessToken)
            getUserContacts()
        }

    }

    fun getIndex(contact: Contact): Int = _contactList.value.indexOf(contact)

    fun changeSelectedList(contact: Contact) {
        _selectedContactsList.value = _selectedContactsList.value.toMutableList().apply {
            if (contact.isSelected) {
                add(contact)
            } else {
                remove(contact)
            }

        }
    }

    fun deleteSelected() {
        _contactList.value = _contactList.value.toMutableList().filter { !it.isSelected }
        _selectedContactsList.value = emptyList()
    }


 fun getUserContacts() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = userRepository.getUserContacts(userId, accessToken)
            if (response != null) {
                var userContactsList = response.contacts
                _contactList.value = userContactsList.sortedBy { it.id }

            }
        }
    }

    private suspend fun getAccessToken(): String {
        return dataStore.accessTokenFlow.first()
    }

    private suspend fun getUserId(): Int {
        return dataStore.userIdFlow.first()
    }


}