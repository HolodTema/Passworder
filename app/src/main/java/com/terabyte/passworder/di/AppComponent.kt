package com.terabyte.passworder.di

import com.terabyte.passworder.activity.LoginActivity
import com.terabyte.passworder.viewmodel.LoginViewModel
import com.terabyte.passworder.viewmodel.MainViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(activity: LoginActivity)

    fun inject(viewModel: LoginViewModel)

    fun inject(viewModel: MainViewModel)
}