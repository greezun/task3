package shpp.maslak.task3.util

import android.app.Application
import shpp.maslak.task3.data.RepositoryContacts


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        app = this
        manager = RepositoryContacts()
    }

    companion object {
        private lateinit var app: App
        val instance: App get() = app
        lateinit var manager: ContactManager


    }


}