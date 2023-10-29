package uz.os3ketchup.myhelper

import android.app.Application
import uz.os3ketchup.myhelper.di.DaggerAuthComponent

class AuthApp : Application() {
    val component by lazy {
        DaggerAuthComponent.factory().create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }
}