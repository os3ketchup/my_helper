package uz.os3ketchup.myhelper.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import uz.os3ketchup.myhelper.presentation.viewmodels.AuthViewModel

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    fun bindAuthViewModel(viewModel:AuthViewModel):ViewModel
}