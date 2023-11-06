package uz.os3ketchup.myhelper.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import uz.os3ketchup.myhelper.data.UserRepositoryImpl
import uz.os3ketchup.myhelper.domain.UserRepository


@Module
interface DomainModule {
    @ApplicationScope
    @Binds
    fun provideUserRepository(impl: UserRepositoryImpl): UserRepository
}