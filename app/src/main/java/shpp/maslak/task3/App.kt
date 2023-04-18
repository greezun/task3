package shpp.maslak.task3

import android.app.Application
import shpp.maslak.task3.data.RepositoryContacts
import shpp.maslak.task3.data.ContactManager


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