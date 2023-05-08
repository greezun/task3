package shpp.maslak.task3.ui.fragments.main.contactDetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import shpp.maslak.task3.databinding.FragmentContactDetailBinding
import shpp.maslak.task3.ui.base.BaseFragment
import shpp.maslak.task3.util.setContactPhoto

@AndroidEntryPoint
class ContactDetailFragment: BaseFragment<FragmentContactDetailBinding>(FragmentContactDetailBinding::inflate){

    private val args by navArgs<ContactDetailFragmentArgs>()
    private val viewModel by viewModels<ContactDetailViewModel> ()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()

    }
     private fun setObservers() {
        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.contactsFlow.collect{ contacts ->
                    val contact = contacts.firstOrNull{
                        it.id == args.contactId
                    }
                    if (contact != null) {
                        binding.userName.text = contact.userName
                        binding.career.text = contact.career
                        binding.picture.setContactPhoto(contact.avatar)
                        binding.address.text = contact.address
                    }

                }
            }
        }
    }




}