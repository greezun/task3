package shpp.maslak.task3.ui.fragments.myContact


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import shpp.maslak.task3.R
import shpp.maslak.task3.databinding.ItemContactBinding
import shpp.maslak.task3.data.model.Contact
import shpp.maslak.task3.util.setContactPhoto


interface ContactActionListener {
    fun onContactDelete(contact: Contact)
    fun onContactDetail(contact: Contact)
}

class ContactAdapter(private val contactActionListener: ContactActionListener) :
    ListAdapter<Contact, ContactAdapter.ContactHolder>(ContactComparator()), View.OnClickListener {

    //todo as for me clicklisteners should be in viewHolder class
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemContactBinding.inflate(inflater, parent, false)
        binding.basket.setOnClickListener(this)
        binding.cvItemOfContact.setOnClickListener(this)

        return ContactHolder(binding)
    }

    // todo not necessary to override
    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        val contact = getItem(position)
        with(holder.binding) {
            basket.tag = contact
            cvItemOfContact.tag = contact
            textViewUserName.text = contact.userName
            textViewCareer.text= contact.career
            imageViewAvatar.setContactPhoto(contact.avatar)
        }

    }
    inner class ContactHolder(
        val binding: ItemContactBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onClick(view: View) {
        val contact = view.tag as Contact
        Log.d("myLog", "contact $contact")
        when (view.id) {
            R.id.basket -> contactActionListener.onContactDelete(contact)
            R.id.cvItemOfContact ->contactActionListener.onContactDetail(contact)

        }

    }
    class ContactComparator: DiffUtil.ItemCallback<Contact>() {
        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem == newItem

        }
    }
}
