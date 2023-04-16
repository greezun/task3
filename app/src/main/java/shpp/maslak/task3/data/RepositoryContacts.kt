package shpp.maslak.task3.data

import kotlinx.coroutines.flow.StateFlow
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
            if(!contains(contact)) {
                add(index, contact)
            }
        }
    }

    override fun deleteContact(contact: Contact){
        _contactList.value = _contactList.value.toMutableList().apply {
            remove(contact)
        }

    }

    override fun getContact(index: Int): Contact = contactList.value[index]
    override fun getIndex(contact: Contact): Int {
        return _contactList.value.indexOf(contact)
    }

    override fun getContactList():StateFlow<List<Contact>>  = contactList

}