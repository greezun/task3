package shpp.maslak.task3.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import shpp.maslak.task3.data.ContactManager
import shpp.maslak.task3.data.RepositoryContacts
import shpp.maslak.task3.data.repository.UserRepositoryImpl
import shpp.maslak.task3.domain.repository.UserRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindContactManager(
        manager: RepositoryContacts
    ): ContactManager

    @Binds
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository
}