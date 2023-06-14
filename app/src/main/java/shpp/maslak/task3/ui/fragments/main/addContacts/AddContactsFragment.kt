package shpp.maslak.task3.ui.fragments.main.addContacts


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle

import androidx.recyclerview.widget.LinearLayoutManager

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import shpp.maslak.task3.R
import shpp.maslak.task3.data.model.User

import shpp.maslak.task3.ui.base.BaseFragment

import shpp.maslak.task3.databinding.FragmentAddContactsBinding
import shpp.maslak.task3.ui.activities.ContactActivity
import shpp.maslak.task3.ui.fragments.main.addContacts.adapter.AddContactsAdapter
import shpp.maslak.task3.ui.fragments.main.addContacts.adapter.actionListener.AddContactAction


@AndroidEntryPoint
class AddContactsFragment :
    BaseFragment<FragmentAddContactsBinding>(FragmentAddContactsBinding::inflate) {


    private val adapter: AddContactsAdapter by lazy { createAdapter() }
    private val viewModel by viewModels<AddContactsViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindFields()
        setObservers()
        setListeners()


    }

    override fun onResume() {
        super.onResume()
        (activity as ContactActivity).supportActionBar?.title = getString(R.string.title_my_contacts)
    }


     private  fun setListeners() {


    }





    private fun bindFields() {
        val manager = LinearLayoutManager(requireContext())
        with(binding) {

            recyclerView.layoutManager = manager
            recyclerView.adapter = adapter
        }
    }




    private fun setObservers() {

        contactsListObserver()
    }

    private fun contactsListObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.contactState.collect { list ->
                    adapter.submitList(list)
                }
            }
        }
    }




    private fun createAdapter(): AddContactsAdapter {
        return AddContactsAdapter(actionListener = object : AddContactAction {
            override fun addContactToUserContactsList(contact: User) {

            }

        })
    }


}


