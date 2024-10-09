package com.terabyte.passworder.di

import android.content.Context
import com.terabyte.passworder.data.DataStoreManager
import com.terabyte.passworder.util.HashManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DataModule::class, UtilModule::class])
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return context
    }
}

@Module
object DataModule {

    @Provides
    @Singleton
    fun provideDataStoreManager(context: Context): DataStoreManager {
        return DataStoreManager(context)
    }
}

@Module
object UtilModule {

    @Provides
    @Singleton
    fun provideHashManager(): HashManager {
        return HashManager()
    }
}