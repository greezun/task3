package shpp.maslak.task3.ui.fragments.myContact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import shpp.maslak.task3.util.ContactManager
import shpp.maslak.task3.util.model.Contact


class ViewModelForContacts(private val manager: ContactManager) : ViewModel() {


    private var _contactList = MutableStateFlow<List<Contact>>(emptyList())
    val contactState = _contactList

    init {
        viewModelScope.launch {
            manager.getContactList().collectLatest { contactList ->
                _contactList.value = contactList
            }
        }
    }

    fun addContact(contact: Contact) {
        manager.addContact(contact)

    }

    fun addContactOnIndex(index: Int, contact: Contact) {
        manager.addContactFromIndex(index, contact)

    }

    fun getContact(index: Int) = manager.getContact(index)
    fun deleteContact(contact: Contact): Int {
        return manager.deleteContact(contact)
    }

}