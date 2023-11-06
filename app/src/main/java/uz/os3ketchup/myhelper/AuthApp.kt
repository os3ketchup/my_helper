package uz.os3ketchup.myhelper

import android.app.Application
import com.google.firebase.Firebase
import com.google.firebase.appcheck.appCheck
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory
import com.google.firebase.database.database
import com.google.firebase.initialize
import uz.os3ketchup.myhelper.di.DaggerAuthComponent

class AuthApp : Application() {
    val component by lazy {
        DaggerAuthComponent.factory().create(this)
    }

    override fun onCreate() {
        Firebase.initialize(context = this)
        Firebase.appCheck.installAppCheckProviderFactory(PlayIntegrityAppCheckProviderFactory.getInstance())
        Firebase.database.setPersistenceEnabled(true)
        component.inject(this)
        super.onCreate()
    }
}