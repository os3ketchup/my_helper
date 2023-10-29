package uz.os3ketchup.myhelper.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import uz.os3ketchup.myhelper.AuthApp
import uz.os3ketchup.myhelper.presentation.MainActivity
import uz.os3ketchup.myhelper.presentation.fragments.OtpFragment
import uz.os3ketchup.myhelper.presentation.viewmodels.AuthViewModel

@ApplicationScope
@Component(modules = [AuthModule::class,ViewModelModule::class])
interface AuthComponent {

    fun inject(otpFragment:OtpFragment)


    fun inject(application: AuthApp)


    @Component.Factory
    interface Factory{

        fun create(
            @BindsInstance application: Application
        ):AuthComponent
    }
}