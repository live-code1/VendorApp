package com.example.vendorapp.common.di

import android.content.Context
import androidx.annotation.Keep
import com.example.vendorapp.common.base.RetrofitInstances
import com.example.vendorapp.common.data.remote.api.VendorApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Keep
@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun vendor(
        remoteDataSource: RetrofitInstances,
        @ApplicationContext context: Context
    ): VendorApi {
        return remoteDataSource.buildApi(
            VendorApi::class.java,
            context
        )
    }


}