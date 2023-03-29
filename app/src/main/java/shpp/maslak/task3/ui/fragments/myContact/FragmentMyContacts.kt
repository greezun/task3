package shpp.maslak.task3.ui.fragments.myContact

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import shpp.maslak.task3.R
import shpp.maslak.task3.databinding.FragmentMyContactsBinding
import shpp.maslak.task3.ui.base.BaseFragment
import shpp.maslak.task3.util.model.Contact


class FragmentMyContacts: BaseFragment<FragmentMyContactsBinding>(FragmentMyContactsBinding::inflate)
    {
    private lateinit var addContact: AppCompatTextView
    private val adapter: ContactAdapter by lazy { createAdapter() }
    private val contactViewModel: ViewModelForContacts by viewModels()




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindFields()
        setObservers()
        setListeners()
    }



    override fun setListeners() {
        onClickAddContactListener()
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

    private fun onClickAddContactListener() {
        binding.tvAddContact.setOnClickListener {
            val contactAdder = DialogAddContact()
            contactAdder.show(parentFragmentManager, DialogAddContact.TAG)
        }
    }

    override fun setObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                contactViewModel.contactState.collect { list ->
                    adapter.submitList(list)
                    Log.d("myLog","observ ${list.toString()}")
                }
            }
        }
    }

//    override fun onConfirmButtonClicked(contact: Contact) {
//        contactViewModel.addContact(contact)
//        Snackbar.make(binding.root, MESSAGE_ADD_CONTACT , Snackbar.LENGTH_SHORT)
//            .show()
//    }

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
        Snackbar.make(binding.root, MESSAGE_DELETE , Snackbar.LENGTH_LONG)
            .setAction(SNACKBAR_ACTION_BUTTON_TEXT) {
                contactViewModel.addContactOnIndex(index, contact)
            }
            .show()
    }
    companion object{
        private const val MESSAGE_ADD_CONTACT = "Contact added"
        private const val MESSAGE_DELETE = "Contact has been deleted"
        private const val SNACKBAR_ACTION_BUTTON_TEXT = "UNDO"

        @JvmStatic private val KEY_LABEL = "KEY_LABEL"



        @JvmStatic
        fun getInstance(toolbarLabel: Int): FragmentMyContacts {
            val args: Bundle = Bundle().apply {
                putInt(KEY_LABEL, toolbarLabel)
            }
            val fragment = FragmentMyContacts()
            fragment.arguments = args

            return fragment
        }
    }


}


