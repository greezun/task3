package shpp.maslak.task3.data

import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow

import kotlinx.coroutines.flow.StateFlow
import shpp.maslak.task3.util.ContactManager
import shpp.maslak.task3.data.model.Contact
import shpp.maslak.task3.data.model.ContactGenerator

class RepositoryContacts: ContactManager {

    private val contactProvider = ContactGenerator()
    private var _contactList = contactProvider.getContacts()
    private val contactList: StateFlow<List<Contact>> = _contactList

    override fun addContact(contact: Contact) {
        _contactList.value = _contactList.value.toMutableList().apply {
            add(contact)
        }
    }

    override fun addContactFromIndex(index: Int, contact: Contact) {
        _contactList.value = _contactList.value.toMutableList().apply {
            add(index, contact)
        }
    }

    override fun deleteContact(contact: Contact): Int {
        var index: Int
        _contactList.value = _contactList.value.toMutableList().apply {
            index = indexOf(contact)
            remove(contact)

            Log.d("myLog", "_contactList.value  ${_contactList.value}" )
        }
        return index
    }

    override fun getContact(index: Int): Contact = contactList.value[index]

    override fun getContactList():StateFlow<List<Contact>>  = contactList

}