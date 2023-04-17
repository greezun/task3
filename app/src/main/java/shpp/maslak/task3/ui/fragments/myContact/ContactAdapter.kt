package shpp.maslak.task3.ui.fragments.myContact


import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import shpp.maslak.task3.databinding.ItemContactBinding
import shpp.maslak.task3.data.model.Contact
import shpp.maslak.task3.util.ContactDifUtil
import shpp.maslak.task3.util.setContactPhoto
import kotlin.properties.Delegates


class ContactAdapter(
    private val contactActionListener: ContactActionListener
) :
    ListAdapter<Contact, ContactAdapter.ContactHolder>(ContactDifUtil()) {

    var multiselectMode = false

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

                if (multiselectMode) {
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
                cvItemOfContact.setOnLongClickListener {
                    contactActionListener.setMultiselectMode(true)
                    true
                }
                cvItemOfContact.setOnClickListener {
                    if (multiselectMode) {
                        cbItemCheckBox.isChecked = !cbItemCheckBox.isChecked
                        contact.isSelected = cbItemCheckBox.isChecked
                        Log.d("myLog", "contact selected = ${contact.isSelected}")
                    } else {
                        contactActionListener.onContactDetail(contact)
                    }
                }
                basket.setOnClickListener { contactActionListener.onContactDelete(contact) }

            }
        }
    }
}

