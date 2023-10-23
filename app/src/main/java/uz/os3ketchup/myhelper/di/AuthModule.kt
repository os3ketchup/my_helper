package uz.os3ketchup.myhelper.di

import dagger.Module
import dagger.Provides
import uz.os3ketchup.bozorhelper.data.AuthRepositoryImpl
import uz.os3ketchup.myhelper.domain.AuthRepository

@Module
class AuthModule {
    @Provides
    fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository {
        return authRepositoryImpl
    }
}
