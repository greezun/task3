package shpp.maslak.task3.ui.fragments.contactDetail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.launch
import shpp.maslak.task3.databinding.FragmentContactDetailBinding
import shpp.maslak.task3.ui.base.BaseFragment
import shpp.maslak.task3.App
import shpp.maslak.task3.util.setContactPhoto
import shpp.maslak.task3.util.viewModelCreator

class ContactDetailFragment: BaseFragment<FragmentContactDetailBinding>(FragmentContactDetailBinding::inflate){

    private val args by navArgs<ContactDetailFragmentArgs>()
    private val viewModel: ContactDetailViewModel by viewModelCreator { ContactDetailViewModel(args.contactId, App.manager) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setObservers()

    }
    override fun setObservers() {
        lifecycleScope.launch{
            Log.d("myLg", "start observ")
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.contactFlow.collect{ contact ->
                    if (contact != null) {
                        Log.d("myLg", "contact detail $contact")
                        binding.userName.text = contact.userName
                        binding.career.text = contact.career
                        binding.picture.setContactPhoto(contact.avatar)
                        binding.address.text = contact.address
                    }

                }
            }
        }
    }

    override fun setListeners() {

    }


}