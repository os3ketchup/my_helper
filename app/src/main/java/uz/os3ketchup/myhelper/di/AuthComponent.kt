package uz.os3ketchup.myhelper.di

import dagger.Component
import uz.os3ketchup.myhelper.presentation.MainActivity


@Component(modules = [AuthModule::class])
interface AuthComponent {

    fun inject(activity: MainActivity)
}