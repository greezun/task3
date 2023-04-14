package shpp.maslak.task3.ui.fragments.contactDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import shpp.maslak.task3.data.ContactManager
import shpp.maslak.task3.data.model.Contact

class ContactDetailViewModel(
    private val contactId: Long,
    private val repositoryContacts: ContactManager
) : ViewModel() {

    private val _contactFlow = MutableStateFlow<Contact?>(null)

    val contactFlow: StateFlow<Contact?> = _contactFlow

    init {
        viewModelScope.launch {
            repositoryContacts.getContactList()
                .map { contacts ->
                    contacts.firstOrNull {
                        it.id == contactId
                    }
                }
                .collect{currentContact -> _contactFlow.value = currentContact }
        }

    }


}