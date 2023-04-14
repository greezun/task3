package shpp.maslak.task3.ui.fragments.myContact



import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import shpp.maslak.task3.databinding.ItemContactBinding
import shpp.maslak.task3.data.model.Contact
import shpp.maslak.task3.util.ContactDifUtil
import shpp.maslak.task3.util.setContactPhoto


class ContactAdapter(private val contactActionListener: ContactActionListener) :
    ListAdapter<Contact, ContactAdapter.ContactHolder>(ContactDifUtil()){

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
                basket.tag = contact
                cvItemOfContact.tag = contact
                textViewUserName.text = contact.userName
                textViewCareer.text = contact.career
                imageViewAvatar.setContactPhoto(contact.avatar)

                cvItemOfContact.setOnClickListener{contactActionListener.onContactDetail(contact)}
                basket.setOnClickListener { contactActionListener.onContactDelete(contact) }
            }
        }
    }
}
