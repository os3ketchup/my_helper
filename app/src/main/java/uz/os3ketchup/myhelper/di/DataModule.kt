package uz.os3ketchup.myhelper.di

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides


@Module
class DataModule {

    @ApplicationScope
    @Provides
    fun provideFirebaseDatabase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    @ApplicationScope
    @Provides
    fun provideReference(firebaseDatabase: FirebaseDatabase): DatabaseReference {
        return firebaseDatabase.getReference(USERS_REF)
    }



    companion object {
        const val USERS_REF = "users_ref"
        const val CATEGORIES_REF = "categories_ref"
    }
}