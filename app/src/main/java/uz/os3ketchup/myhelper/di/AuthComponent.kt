package uz.os3ketchup.myhelper.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import uz.os3ketchup.myhelper.AuthApp
import uz.os3ketchup.myhelper.presentation.MainActivity
import uz.os3ketchup.myhelper.presentation.fragments.ChatFragment
import uz.os3ketchup.myhelper.presentation.fragments.MainFragment
import uz.os3ketchup.myhelper.presentation.fragments.OtpFragment
import uz.os3ketchup.myhelper.presentation.fragments.ProductFragment
import uz.os3ketchup.myhelper.presentation.fragments.UserFragment

@ApplicationScope
@Component(
    modules = [AuthModule::class,
        ViewModelModule::class,
        DataModule::class,
        DomainModule::class]
)
interface AuthComponent {

    fun inject(otpFragment: OtpFragment)
    fun inject(application: AuthApp)
    fun inject(userFragment: UserFragment)
    fun inject(mainActivity: MainActivity)
    fun inject(mainFragment: MainFragment)
    fun inject(chatFragment: ChatFragment)
    fun inject(productFragment: ProductFragment)


    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): AuthComponent
    }
}