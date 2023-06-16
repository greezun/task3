package shpp.maslak.task3.ui.fragments.main.myContacts


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import shpp.maslak.task3.R
import shpp.maslak.task3.data.model.Contact
import shpp.maslak.task3.data.model.User
import shpp.maslak.task3.databinding.FragmentMyContactsBinding
import shpp.maslak.task3.ui.base.BaseFragment
import shpp.maslak.task3.ui.activities.ContactActivity
import shpp.maslak.task3.ui.fragments.main.myContacts.adapter.ContactAdapter
import shpp.maslak.task3.ui.fragments.main.myContacts.adapter.actionListener.ContactActionListener
import shpp.maslak.task3.ui.fragments.main.pagerFragment.FragmentPagerDirections
import shpp.maslak.task3.util.Constants


@AndroidEntryPoint
class MyContactsFragment :
    BaseFragment<FragmentMyContactsBinding>(FragmentMyContactsBinding::inflate) {

    private lateinit var buttonAddContact: AppCompatTextView
    private lateinit var buttonDelete: AppCompatImageView
    private val adapter: ContactAdapter by lazy { createAdapter() }
    private val contactViewModel by viewModels<ContactsViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindFields()
        setObservers()
        setListeners()

    }



    override fun onResume() {
        super.onResume()
        contactViewModel.getUserContacts()
        (activity as ContactActivity).supportActionBar?.title =
            getString(R.string.title_my_contacts)
    }


    private fun setListeners() {
        onSelectedContactDeleteListener()
        listenSwipe()
        addContactTextViewListener()

    }

    private fun addContactTextViewListener() {
        binding.tvAddContact.setOnClickListener {
            val direction = FragmentPagerDirections.actionFragmentPagerToAddContactsFragment()
            findNavController().navigate(direction)
        }
    }

    private fun listenSwipe() {
        val callBack = getSwipeCallBack()
        val itemTouchHelper = ItemTouchHelper(callBack)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)


    }

    private fun onSelectedContactDeleteListener() {
        buttonDelete.setOnClickListener {
            contactViewModel.deleteSelected()
        }
    }

    private fun bindFields() {
        val manager = LinearLayoutManager(requireContext())
        with(binding) {
            buttonAddContact = tvAddContact
            buttonDelete = ivBasketMultiselect
            recyclerView.layoutManager = manager
            recyclerView.adapter = adapter
        }
    }

    private fun getSwipeCallBack(): ItemTouchHelper.SimpleCallback {
        return object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val index = viewHolder.adapterPosition
                val contact = contactViewModel.getContact(index)
                contactViewModel.deleteContact(contact)
                showDeleteMessage( contact)
            }

            override fun getSwipeDirs(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                return if (adapter.isMultiselectMode) 0 else super.getSwipeDirs(
                    recyclerView,
                    viewHolder
                )
            }
        }
    }


    private fun setObservers() {
        multiselectModeObserver()
        contactsListObserver()
    }

    private fun contactsListObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                contactViewModel.contactState.collect { list ->
                    Log.d("myLog", "my contacts list $list")

                    adapter.submitList(list)
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun multiselectModeObserver() {
        lifecycleScope.launch {
            contactViewModel.multiselectMode.collect { list ->
                val isMultiselect = list.isNotEmpty()
                val basket = binding.ivBasketMultiselect
                basket.visibility = if (isMultiselect) View.VISIBLE else View.GONE
                adapter.isMultiselectMode = isMultiselect
                adapter.notifyDataSetChanged()
            }
        }
    }


    private fun createAdapter(): ContactAdapter {
        return ContactAdapter(contactActionListener = object : ContactActionListener {
            override fun onDelete(contact: Contact) {

                contactViewModel.deleteContact(contact)
                showDeleteMessage( contact)
            }

            override fun onContactHolder(contact: Contact){
//                    MyContactsFragmentDirections.actionGlobalFragmentContactDetail(contact.id)
//                findNavController().navigate(direction)
            }

            override fun onLongClick(contact: Contact) {
                contact.isSelected = true
                contactViewModel.changeSelectedList(contact)

            }

            override fun onCheckBox(isSelected: Boolean, contact: Contact) {
                contact.isSelected = isSelected
                contactViewModel.changeSelectedList(contact)
            }

        })
    }

    private fun showDeleteMessage( contact: Contact) {
        Snackbar.make(binding.root, Constants.MESSAGE_DELETE, Snackbar.LENGTH_LONG)
            .setAction(Constants.SNACKBAR_ACTION_BUTTON_TEXT) {
                contactViewModel.addContact( contact)
            }
            .show()
    }
}