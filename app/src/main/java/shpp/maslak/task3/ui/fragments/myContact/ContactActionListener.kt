package shpp.maslak.task3.ui.fragments.myContact

import shpp.maslak.task3.data.model.Contact

interface ContactActionListener {
    fun onDelete(contact: Contact)
    fun onContactHolder(contact: Contact)
    fun onLongClick(contact: Contact)
    fun onCheckBox(isSelected: Boolean, contact: Contact)




}