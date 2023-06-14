package shpp.maslak.task3.ui.fragments.main.addContacts.adapter.diffUtill

import androidx.recyclerview.widget.DiffUtil

import shpp.maslak.task3.data.model.User

class UserDiffUtilCallback: DiffUtil.ItemCallback<User>() {
    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem

    }
}