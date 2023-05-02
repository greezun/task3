package shpp.maslak.task3.ui.fragments.main.myContact.adapter.diffUtill

import androidx.recyclerview.widget.DiffUtil
import shpp.maslak.task3.data.model.Contact

class DiffUtilCallback: DiffUtil.ItemCallback<Contact>() {
    override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem == newItem

    }
}