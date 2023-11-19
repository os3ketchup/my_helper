package uz.os3ketchup.myhelper.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import uz.os3ketchup.myhelper.presentation.viewmodels.AuthViewModel
import uz.os3ketchup.myhelper.presentation.viewmodels.MainViewModel
import uz.os3ketchup.myhelper.presentation.viewmodels.ProductViewModel

@Module
interface ViewModelModule {

    @ApplicationScope
    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    fun bindAuthViewModel(viewModel:AuthViewModel):ViewModel

    @ApplicationScope
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel:MainViewModel):ViewModel

    @ApplicationScope
    @Binds
    @IntoMap
    @ViewModelKey(ProductViewModel::class)
    fun bindProductViewModel(viewModel:ProductViewModel):ViewModel


}