package shpp.maslak.task3.ui.fragments.main.addContacts.adapter.actionListener

import shpp.maslak.task3.data.model.User

interface AddContactActionListener {
    fun addContactToUserContactsList(contact: User)
}