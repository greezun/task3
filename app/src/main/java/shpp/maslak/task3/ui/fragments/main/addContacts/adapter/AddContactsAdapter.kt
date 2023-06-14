package shpp.maslak.task3.ui.fragments.main.addContacts.adapter



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import shpp.maslak.task3.data.model.User
import shpp.maslak.task3.databinding.ItemAddContactsBinding
import shpp.maslak.task3.ui.fragments.main.addContacts.adapter.actionListener.AddContactAction
import shpp.maslak.task3.ui.fragments.main.addContacts.adapter.diffUtill.UserDiffUtilCallback
import javax.inject.Inject


class AddContactsAdapter @Inject constructor(
private val actionListener: AddContactAction
) : ListAdapter<User, AddContactsAdapter.AddContactsViewHolder>(UserDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddContactsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAddContactsBinding.inflate(inflater, parent, false)
        return AddContactsViewHolder(binding)
    }


    override fun onBindViewHolder(holder: AddContactsViewHolder, position: Int) {
        val contact = getItem(position)
        holder.bindTo(contact)
    }

    inner class AddContactsViewHolder(
        private val binding: ItemAddContactsBinding,

        ) : RecyclerView.ViewHolder(binding.root) {

        fun bindTo(contact: User) {
            with(binding) {
                textViewUserName.text = contact.email
                textViewCareer.text = contact.career
            }
            setClickListeners(binding, contact)
        }

        private fun setClickListeners(binding: ItemAddContactsBinding, contact: User) {
            with(binding){
                imageViewPlus.setOnClickListener {
                    actionListener.addContactToUserContactsList(contact)
                    imageViewPlus.visibility = View.GONE
                    imageViewDone.visibility = View.VISIBLE
                }
            }


        }

    }
}




