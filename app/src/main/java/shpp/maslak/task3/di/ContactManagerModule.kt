package shpp.maslak.task3.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import shpp.maslak.task3.data.ContactManager
import shpp.maslak.task3.data.RepositoryContacts

@Module
@InstallIn(SingletonComponent::class)
abstract class ContactManagerModule {

    @Binds
    abstract fun bindContactManager(
        manager: RepositoryContacts
    ): ContactManager


}