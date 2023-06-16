package shpp.maslak.task3.ui.fragments.main.myContacts.adapter


import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import shpp.maslak.task3.databinding.ItemContactBinding
import shpp.maslak.task3.data.model.Contact
import shpp.maslak.task3.ui.fragments.main.myContacts.adapter.actionListener.ContactActionListener
import shpp.maslak.task3.ui.fragments.main.myContacts.adapter.diffUtill.DiffUtilCallback


class ContactAdapter(
    private val contactActionListener: ContactActionListener
) : ListAdapter<Contact, ContactAdapter.ContactViewHolder>(DiffUtilCallback()) {

     var isMultiselectMode = false
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemContactBinding.inflate(inflater, parent, false)
        return ContactViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = getItem(position)
        holder.bindTo(contact)
    }

    inner class ContactViewHolder(
        private val binding: ItemContactBinding,

    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindTo(contact: Contact) {
            with(binding) {
                if (isMultiselectMode) {
                    basket.visibility = View.GONE
                    cbItemCheckBox.visibility = View.VISIBLE
                    cvItemOfContact.setCardBackgroundColor(Color.GRAY)
                    cbItemCheckBox.isChecked = contact.isSelected
                } else {
                    basket.visibility = View.VISIBLE
                    cbItemCheckBox.visibility = View.GONE
                    cvItemOfContact.setCardBackgroundColor(Color.WHITE)
                }
                basket.tag = contact
                cvItemOfContact.tag = contact
                textViewUserName.text = contact.email
                textViewCareer.text = contact.id.toString()
//                imageViewAvatar.setContactPhoto(contact.image)
            }
            setClickListeners(binding, contact)
        }

        private fun setClickListeners(binding: ItemContactBinding, contact:Contact) {

            with(binding){
                if(!isMultiselectMode) {
                    cvItemOfContact.setOnLongClickListener {
                        contactActionListener.onLongClick(contact)
                        true
                    }
                } else cvItemOfContact.setOnLongClickListener(null)

                cvItemOfContact.setOnClickListener {
                    if (isMultiselectMode) {
                        cbItemCheckBox.isChecked = !cbItemCheckBox.isChecked
                        contactActionListener.onCheckBox(cbItemCheckBox.isChecked, contact)

                    } else {
                        contactActionListener.onContactHolder(contact)
                    }
                }
                basket.setOnClickListener { contactActionListener.onDelete(contact) }
            }

        }
    }


}

