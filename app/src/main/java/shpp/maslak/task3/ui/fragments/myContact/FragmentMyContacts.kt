package shpp.maslak.task3.ui.fragments.myContact

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import shpp.maslak.task3.R
import shpp.maslak.task3.databinding.FragmentMyContactsBinding
import shpp.maslak.task3.ui.base.BaseFragment
import shpp.maslak.task3.util.App
import shpp.maslak.task3.util.model.Contact
import shpp.maslak.task3.util.viewModelCreator


class FragmentMyContacts :
    BaseFragment<FragmentMyContactsBinding>(FragmentMyContactsBinding::inflate) {
    private lateinit var addContact: AppCompatTextView
    private val adapter: ContactAdapter by lazy { createAdapter() }
    private val contactViewModel: ViewModelForContacts by viewModelCreator { ViewModelForContacts(
        App.manager) }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindFields()
        setObservers()
        setListeners()
    }


    override fun setListeners() {
        onSwipeToDeleteListener()
    }


    private fun bindFields() {
        val manager = LinearLayoutManager(requireContext())
        with(binding) {
            addContact = tvAddContact
            recyclerView.layoutManager = manager
            recyclerView.adapter = adapter
        }
    }

    private fun onSwipeToDeleteListener() {
        val calBack = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val index = viewHolder.adapterPosition
                val contact = contactViewModel.getContact(index)
                contactViewModel.deleteContact(contact)
                showDeleteMessage(index, contact)
            }
        }
        val myHelper = ItemTouchHelper(calBack)
        myHelper.attachToRecyclerView(binding.recyclerView)

    }


    override fun setObservers() {
//        lifecycleScope.launch{
//            repeatOnLifecycle(Lifecycle.State.STARTED){
//                contactViewModel.isShowSnackbarFlow.collect{
//                    flag -> if (flag) showDeleteMessage()
//                }
//            }
//        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                contactViewModel.contactState.collect { list ->
                    adapter.submitList(list)
                    Log.d("myLog", "observ ${list.toString()}")
                }
            }
        }
    }


    private fun createAdapter(): ContactAdapter {
        return ContactAdapter(contactActionListener = object : ContactActionListener {
            override fun onContactDelete(contact: Contact) {
                val index = contactViewModel.deleteContact(contact)
                showDeleteMessage(index, contact)
            }

            override fun onContactDetail(contact: Contact) {
                findNavController().navigate(R.id.fragmentContactDetail)
            }


        })
    }

    private fun showDeleteMessage(index: Int, contact: Contact) {
        Snackbar.make(binding.root, MESSAGE_DELETE, Snackbar.LENGTH_LONG)
            .setAction(SNACKBAR_ACTION_BUTTON_TEXT) {
                contactViewModel.addContactOnIndex(index, contact)
            }
            .show()
    }

    companion object {
        private const val MESSAGE_DELETE = "Contact has been deleted"
        private const val SNACKBAR_ACTION_BUTTON_TEXT = "UNDO"
    }


}


