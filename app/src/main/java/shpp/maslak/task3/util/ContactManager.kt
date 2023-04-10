package shpp.maslak.task3.util


import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

import shpp.maslak.task3.data.model.Contact

interface ContactManager  {

    fun addContact (contact: Contact)
    fun addContactFromIndex(index: Int, contact: Contact)
    fun deleteContact(contact: Contact): Int
    fun getContact(index: Int): Contact
    fun getContactList(): StateFlow<List<Contact>>
}