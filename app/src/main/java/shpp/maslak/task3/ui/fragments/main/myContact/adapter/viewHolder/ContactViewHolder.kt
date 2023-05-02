package shpp.maslak.task3.ui.fragments.main.myContact.adapter.viewHolder

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import shpp.maslak.task3.data.model.Contact
import shpp.maslak.task3.databinding.ItemContactBinding
import shpp.maslak.task3.ui.fragments.main.myContact.adapter.actionListener.ContactActionListener
import shpp.maslak.task3.util.setContactPhoto

class ContactViewHolder(
    private val binding: ItemContactBinding,
    private val contactActionListener: ContactActionListener,
    private var isMultiselectMode:Boolean
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