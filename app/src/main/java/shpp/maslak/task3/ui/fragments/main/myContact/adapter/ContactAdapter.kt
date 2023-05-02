package shpp.maslak.task3.ui.fragments.main.myContact.adapter


import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import shpp.maslak.task3.databinding.ItemContactBinding
import shpp.maslak.task3.data.model.Contact
import shpp.maslak.task3.ui.fragments.main.myContact.adapter.actionListener.ContactActionListener
import shpp.maslak.task3.ui.fragments.main.myContact.adapter.diffUtill.DiffUtilCallback
import shpp.maslak.task3.ui.fragments.main.myContact.adapter.viewHolder.ContactViewHolder
import shpp.maslak.task3.util.setContactPhoto


class ContactAdapter(
    private val contactActionListener: ContactActionListener
) : ListAdapter<Contact, ContactViewHolder>(DiffUtilCallback()) {

     var isMultiselectMode = false
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemContactBinding.inflate(inflater, parent, false)

        return ContactViewHolder(binding, contactActionListener, isMultiselectMode)
    }


    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = getItem(position)
        holder.bindTo(contact)
    }


}

