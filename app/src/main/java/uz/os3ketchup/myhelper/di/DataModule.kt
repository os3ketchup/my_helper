package uz.os3ketchup.myhelper.di

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Named


@Module
class DataModule {

    @ApplicationScope
    @Provides
    fun provideFirebaseDatabase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    @ApplicationScope
    @Provides
    @Named(USERS_REF)
    fun provideReference(firebaseDatabase: FirebaseDatabase): DatabaseReference {
        return firebaseDatabase.getReference(USERS_REF)
    }

    @ApplicationScope
    @Provides
    @Named(CATEGORIES_REF)
    fun provideAnotherReference(firebaseDatabase: FirebaseDatabase): DatabaseReference {
        return firebaseDatabase.getReference(CATEGORIES_REF)
    }

    @ApplicationScope
    @Provides
    @Named(PRODUCTS_REF)
    fun provideProductReference(firebaseDatabase: FirebaseDatabase): DatabaseReference {
        return firebaseDatabase.getReference(PRODUCTS_REF)
    }

    companion object {
        const val USERS_REF = "users_ref"
        const val CATEGORIES_REF = "categories_ref"
        const val PRODUCTS_REF = "products_ref"
    }
}