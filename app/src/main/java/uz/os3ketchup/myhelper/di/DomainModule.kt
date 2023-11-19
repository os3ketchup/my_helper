package uz.os3ketchup.myhelper.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import uz.os3ketchup.myhelper.data.CategoryRepositoryImpl
import uz.os3ketchup.myhelper.data.OrderRepositoryImpl
import uz.os3ketchup.myhelper.data.ProductRepositoryImpl
import uz.os3ketchup.myhelper.data.UserRepositoryImpl
import uz.os3ketchup.myhelper.domain.CategoryRepository
import uz.os3ketchup.myhelper.domain.OrderRepository
import uz.os3ketchup.myhelper.domain.ProductRepository
import uz.os3ketchup.myhelper.domain.UserRepository


@Module
interface DomainModule {
    @ApplicationScope
    @Binds
    fun provideUserRepository(impl: UserRepositoryImpl): UserRepository

    @ApplicationScope
    @Binds
    fun provideCategoryRepository(impl: CategoryRepositoryImpl): CategoryRepository

    @ApplicationScope
    @Binds
    fun provideProductRepository(impl: ProductRepositoryImpl): ProductRepository

    @ApplicationScope
    @Binds
    fun provideOrderRepository(impl: OrderRepositoryImpl): OrderRepository
}