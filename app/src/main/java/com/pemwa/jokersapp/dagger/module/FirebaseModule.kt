package com.pemwa.jokersapp.dagger.module

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by petermwash on 16/05/19.
 */

@Module
@Singleton
class FirebaseModule {

    @Provides
    fun firebaseAuth() : FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun firebaseDatabase() : FirebaseDatabase = FirebaseDatabase.getInstance()
}