package shpp.maslak.task3.ui.fragments.main.myContact


import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import shpp.maslak.task3.databinding.ItemContactBinding
import shpp.maslak.task3.data.model.Contact
import shpp.maslak.task3.util.ContactDifUtil
import shpp.maslak.task3.util.setContactPhoto


class ContactAdapter(
    private val contactActionListener: ContactActionListener
) :
    ListAdapter<Contact, ContactAdapter.ContactHolder>(ContactDifUtil()) {

    var isMultiselectMode = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemContactBinding.inflate(inflater, parent, false)

        return ContactHolder(binding)
    }


    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        val contact = getItem(position)
        holder.bindTo(contact)
    }

    inner class ContactHolder(
        private val binding: ItemContactBinding
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
                textViewUserName.text = contact.userName
                textViewCareer.text = contact.career
                imageViewAvatar.setContactPhoto(contact.avatar)
            }
            setClickListeners(binding, contact)
        }

        private fun setClickListeners(binding: ItemContactBinding, contact: Contact) {
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

