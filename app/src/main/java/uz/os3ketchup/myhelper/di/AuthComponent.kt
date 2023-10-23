package uz.os3ketchup.myhelper.di

import dagger.Component
import uz.os3ketchup.myhelper.MainActivity
import uz.os3ketchup.myhelper.di.AuthModule


@Component(modules = [AuthModule::class])
interface AuthComponent {

    fun inject(activity: MainActivity)
}