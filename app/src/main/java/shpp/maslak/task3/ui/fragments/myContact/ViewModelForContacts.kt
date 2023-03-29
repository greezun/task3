package shpp.maslak.task3.ui.fragments.myContact


import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import shpp.maslak.task3.util.model.Contact
import shpp.maslak.task3.util.model.ContactGenerator

private const val IS_GET_PHONE_CONTACTS = false

class ViewModelForContacts : ViewModel() {


    private val contactProvider = ContactGenerator()
    private var _contactFlow =contactProvider.getContacts(IS_GET_PHONE_CONTACTS)
    val contactState: StateFlow<List<Contact>> = _contactFlow


    fun deleteContact(contact: Contact): Int {
        var index: Int
        _contactFlow.value = _contactFlow.value.toMutableList().apply {
            index = indexOf(contact)
            remove(contact)

        }
        return index
    }

    fun addContact(contact: Contact) {
        _contactFlow.value = _contactFlow.value.toMutableList().apply {
            add(contact)
        }
    }

    fun addContactOnIndex(index: Int, contact: Contact) {
        _contactFlow.value = _contactFlow.value.toMutableList().apply {
            add(index, contact)
        }
    }

    fun getContact(index: Int) = _contactFlow.value[index]

//    override fun onConfirmButtonClicked(contact: Contact) {
//        addContact(contact)
//    }



}