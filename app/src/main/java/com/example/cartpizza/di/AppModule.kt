package com.example.cartpizza.di

import android.app.Application
import androidx.room.Room
import com.example.cartpizza.api.PizzaApi
import com.example.cartpizza.data.PizzaDatabase
import com.example.cartpizza.util.PizzaTypeConverters
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(PizzaApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun providePizzaApi(retrofit: Retrofit): PizzaApi =
        retrofit.create(PizzaApi::class.java)

    @Provides
    @Singleton
    fun provideDatabase(app: Application) : PizzaDatabase =
        Room.databaseBuilder(app, PizzaDatabase::class.java, "pizza_database")
            .addTypeConverter(PizzaTypeConverters())
            .build()
}