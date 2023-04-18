package shpp.maslak.task3.ui.fragments.myContact


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import shpp.maslak.task3.data.ContactManager
import shpp.maslak.task3.data.model.Contact


class ContactsViewModel(private val manager: ContactManager) : ViewModel() {


    private var _contactList = MutableStateFlow<List<Contact>>(emptyList())
    val contactState = _contactList

    private var _selectedContactsList = MutableStateFlow<List<Contact>>(emptyList())
    val  multiselectMode: StateFlow<List<Contact>> = _selectedContactsList

    init {
        viewModelScope.launch {
            manager.getContactList().collectLatest { contactList ->
                _contactList.value = contactList

            }
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



}