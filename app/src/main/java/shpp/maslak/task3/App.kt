package shpp.maslak.task3

import android.annotation.SuppressLint
import android.app.Application
import shpp.maslak.task3.data.RepositoryContacts
import shpp.maslak.task3.data.ContactManager
import shpp.maslak.task3.data.LoginData


class App : Application() {

    init {
        app = this
        manager = RepositoryContacts()

    }

    companion object {
        private lateinit var app: App
        val instance: App get() = app
        lateinit var manager: ContactManager



    }


}