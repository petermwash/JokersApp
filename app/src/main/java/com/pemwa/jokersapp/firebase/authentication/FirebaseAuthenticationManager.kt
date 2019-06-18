package com.pemwa.jokersapp.firebase.authentication

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import javax.inject.Inject

class FirebaseAuthenticationManager @Inject constructor(
    //Adding a firebase authentication
    private val authentication: FirebaseAuth
) : FirebaseAuthenticationInterface {

    //LoggingIn a user with email and password
    override fun login(email: String, password: String, onResult: (Boolean) -> Unit) {
        authentication.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                onResult(
                    it.isComplete && it.isSuccessful
                )
            }
    }

    //Creating a unique user with email and password
    //Create a UserProfileChangeRequest which edits user to include a userName
    override fun register(email: String, password: String, userName: String, onResult: (Boolean) -> Unit) {
        authentication.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isComplete && it.isSuccessful) {
                    authentication.currentUser?.updateProfile(
                        UserProfileChangeRequest
                            .Builder()
                            .setDisplayName(userName)
                            .build()
                    )
                    onResult(true)
                }else {
                    onResult(false)
                }
            }
    }

    //Getting the userId of the current user
    override fun getUserId(): String = authentication.currentUser?.uid ?: ""

    //Getting the userName of the current user
    override fun getUserName(): String = authentication.currentUser?.displayName ?: ""

    //LoggingOut the user
    //It closes the Session
    override fun logOut(onResult: () -> Unit) {
        authentication.signOut()

        onResult()
    }
}