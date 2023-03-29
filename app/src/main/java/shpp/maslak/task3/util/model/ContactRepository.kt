package com.mycontacts.model

import kotlinx.coroutines.flow.MutableStateFlow
import shpp.maslak.task3.util.model.Contact
import shpp.maslak.task3.util.model.ContactGenerator

class ContactRepository {

    private val contactProvider = ContactGenerator()
    private var _contactFlow = MutableStateFlow<List<Contact>>(emptyList())

    init {
        if (_contactFlow.value.isEmpty()){
            _contactFlow = if (!IS_GET_PHONE_CONTACTS) contactProvider.generateContacts()
            else contactProvider.getContactFromPhone()
        }
    }



    fun getContacts() = _contactFlow

    fun delete(contact: Contact): Int {
        var index: Int
        _contactFlow.value = _contactFlow.value.toMutableList().apply {
            index = indexOf(contact)
            remove(contact)

        }
        return index
    }

    fun add(contact: Contact) {

    }

    fun add(index: Int, contact: Contact) {
        _contactFlow.value = _contactFlow.value.toMutableList().apply {
            add(index, contact)
        }
    }

    fun getContactOfIndex(index: Int) = _contactFlow.value[index]

    companion object {
        private const val IS_GET_PHONE_CONTACTS = false


    }
}


