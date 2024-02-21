package com.kaiba.coctailoo.data

import com.kaiba.coctailoo.ws.ICocktail
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
    fun getCocktailInstance(): ICocktail {
        return Retrofit.Builder().baseUrl("https://www.thecocktaildb.com/api/json/v1/1/").addConverterFactory(
            GsonConverterFactory.create()).build().create(ICocktail::class.java)
    }
}