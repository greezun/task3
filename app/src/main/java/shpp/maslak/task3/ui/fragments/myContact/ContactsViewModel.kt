package shpp.maslak.task3.ui.fragments.myContact

import android.util.Log
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
                Log.d("myLog", "contact list init ${contactState.value.toString()}")
//                create()
            }
        }
    }




//    private fun create(){
//        _selectedContactsList.value =
//             _contactList.value.filter {
//                it.isSelected
//            }


//        Log.d("myLog", "contact list ${contactState.value.toString()}")
//        Log.d("myLog", "selected contact list ${_selectedContactsList.value.toString()}" )
//
//    }


    fun addContactOnIndex(index: Int, contact: Contact) {
        manager.addContactFromIndex(index, contact)

    }

    fun getContact(index: Int) = manager.getContact(index)
    fun deleteContact(contact: Contact){
        return manager.deleteContact(contact)
    }

    fun getIndex(contact: Contact): Int = manager.getIndex(contact)

    fun addToSelected(contact: Contact){
        _selectedContactsList.value.toMutableList().apply {
            add(contact)
        }
    }

//    fun setMultiselectMode(boolean: Boolean){
//        _multiselectMode.value = boolean
//    }

}