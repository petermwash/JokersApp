package com.pemwa.jokersapp.dagger.module

import com.pemwa.jokersapp.firebase.authentication.FirebaseAuthenticationInterface
import com.pemwa.jokersapp.firebase.authentication.FirebaseAuthenticationManager
import com.pemwa.jokersapp.firebase.database.FirebaseDatabaseInterface
import com.pemwa.jokersapp.firebase.database.FirebaseDatabaseManager
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * Created by petermwash on 15/05/19.
 */

@Module(includes = [FirebaseModule::class])
@Singleton
abstract class InteractionModule {

    @Binds
    abstract fun authentication(authentication: FirebaseAuthenticationManager): FirebaseAuthenticationInterface

    @Binds
    abstract fun database(database: FirebaseDatabaseManager): FirebaseDatabaseInterface
}