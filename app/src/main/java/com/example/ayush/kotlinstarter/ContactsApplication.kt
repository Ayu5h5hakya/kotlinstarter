package com.example.ayush.kotlinstarter

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by ayush on 7/25/17.
 */
class ContactsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val realmConfiguration = RealmConfiguration.Builder().build()
        Realm.setDefaultConfiguration(realmConfiguration)
    }
}