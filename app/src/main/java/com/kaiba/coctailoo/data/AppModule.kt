package com.kaiba.coctailoo.data

import android.util.Log
import com.kaiba.coctailoo.model.Comment
import com.kaiba.coctailoo.model.RealmCocktail
import com.kaiba.coctailoo.ws.ICocktail
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.Credentials
import io.realm.kotlin.mongodb.User
import io.realm.kotlin.mongodb.ext.subscribe
import io.realm.kotlin.mongodb.subscriptions
import io.realm.kotlin.mongodb.sync.SyncConfiguration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
    @Singleton
    @Provides
    fun mongoApp(): App{
        val app = App.create("coctailooservice-jrdmadg")
        CoroutineScope(Dispatchers.IO).launch {
            app.login(Credentials.anonymous())
        }
        return app
    }
    @Singleton
    @Provides
    suspend fun mongoSync(app: App): Realm{
        var myAuthenticatedUser: User? = app.currentUser
        val config =
        // Pass the authenticated user and the set of
            // all objects types you want to be able to sync


            SyncConfiguration.Builder(
                user = myAuthenticatedUser!!,
                schema = setOf(RealmCocktail::class, Comment::class)
            )
                // Define an initial subscription with queries that include
                // the user's lists with incomplete items

                .build()


        // Open the synced realm with the defined configuration
        val realm = Realm.open(config)

// Wait for initial subscriptions to sync to Atlas
        realm.subscriptions.waitForSynchronization()
        return realm
    }
    @Provides
    suspend fun subscriptionMongo(realm: Realm){
        val subscription = realm.query<RealmCocktail>()
        subscription.subscribe()
    }
}