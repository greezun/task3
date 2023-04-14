package shpp.maslak.task3.ui.fragments.myContact

import shpp.maslak.task3.data.model.Contact

interface ContactActionListener {
    fun onContactDelete(contact: Contact)
    fun onContactDetail(contact: Contact)
}