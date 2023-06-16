package shpp.maslak.task3.ui.fragments.main.contactDetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import shpp.maslak.task3.data.model.Contact
import shpp.maslak.task3.databinding.FragmentContactDetailBinding
import shpp.maslak.task3.ui.base.BaseFragment
import shpp.maslak.task3.util.setContactPhoto

@AndroidEntryPoint
class ContactDetailFragment :
    BaseFragment<FragmentContactDetailBinding>(FragmentContactDetailBinding::inflate) {

    private val args by navArgs<ContactDetailFragmentArgs>()
    private val viewModel by viewModels<ContactDetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        observeContactList()
    }

//    private fun observeContactList() {
//        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                viewModel.contactsFlow.collect { contacts ->
//                    val contact = contacts.firstOrNull {
//                        it.id == args.contactId
//                    }
//                    if (contact != null) {
//                        bindFields(contact)
//                    }
//                }
//            }
//        }
//    }

    private fun bindFields(contact: Contact) {
        with(binding) {
            userName.text = contact.userName
            career.text = contact.career
            picture.setContactPhoto(contact.avatar)
            address.text = contact.address
        }
    }
}