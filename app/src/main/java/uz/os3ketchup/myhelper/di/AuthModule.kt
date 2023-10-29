package uz.os3ketchup.myhelper.di

import com.google.firebase.Firebase
import com.google.firebase.app
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import uz.os3ketchup.myhelper.data.AuthRepositoryImpl
import uz.os3ketchup.myhelper.domain.AuthRepository

@Module
class AuthModule {


    @Provides
    fun provideFirebase(): Firebase = Firebase

    @Provides
    fun provideAuthRepository(firebaseAuth: Firebase): AuthRepository {
        return AuthRepositoryImpl(firebaseAuth)
    }


}
