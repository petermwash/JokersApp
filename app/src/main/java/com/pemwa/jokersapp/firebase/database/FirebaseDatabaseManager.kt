package com.pemwa.jokersapp.firebase.database

import com.google.firebase.database.*
import com.pemwa.jokersapp.model.*
import com.pemwa.jokersapp.utils.KEY_FAVORITE
import com.pemwa.jokersapp.utils.KEY_JOKE
import com.pemwa.jokersapp.utils.KEY_USER
import javax.inject.Inject

class FirebaseDatabaseManager @Inject constructor(
    //Adding a firebase database
    private val database: FirebaseDatabase
) : FirebaseDatabaseInterface {

    //This is called right after signing up a user to create the user in the database
    override fun createUser(id: String, name: String, email: String) {
        val user = User(id, name, email)

        database
            .reference          //Getting a reference to the database, which effectively points to the root of the tree/directory
            .child(KEY_USER)    //From the root directory, open up the "user" directory
            .child(id)          //Inside the "user" directory, open up the directory which matches the "id" of this particular user
            .setValue(user)     //Now storing the new user in that directory
    }

    // When listening to jokes, add a child listener to the “joke” directory.
    // On each child we parse the joke and add it to the list
    // We parse the data by calling the getValue(Class) method and the snapshot is parsed to whatever data model you want(JokeModel)
    override fun listenToJokes(onResult: (Joke) -> Unit) {
        database
            .reference
            .child(KEY_JOKE)
            .orderByKey()
            .addChildEventListener(object : ChildEventListener {
                override fun onCancelled(error: DatabaseError) = Unit

                override fun onChildMoved(snapshot: DataSnapshot, p1: String?) = Unit

                override fun onChildChanged(snapshot: DataSnapshot, p1: String?) = Unit

                override fun onChildAdded(snapshot: DataSnapshot, p1: String?) {
                    snapshot.getValue(JokeResponse::class.java)?.run {
                        if (isValid()) {
                            onResult(mapToJoke())
                        }
                    }
                }

                override fun onChildRemoved(p0: DataSnapshot) = Unit
            })
    }

    // To add a new joke, we create a child inside the "jokes" directory and set the the value to the joke object
    // By call the push() method we generate a new key, set the value and add a listener to be notified about completion
    override fun addNewJoke(joke: Joke, onResult: (Boolean) -> Unit) {
        val newJokeReference = database.reference.child(KEY_JOKE).push()
        //val newJoke = joke.copy(id = newJokeReference.key)
        val newJoke = newJokeReference.key?.let {
            joke.copy(id = it)
        }

        newJokeReference.setValue(newJoke)
            .addOnCompleteListener {
                onResult(
                    it.isComplete && it.isSuccessful
                )
            }
    }

    // We store favourite jokes on each user's profile.
    // This is because we cannot request all jokes by ID since queries in the database are limited
    // We will therefore read favorite jokes from each user's profile directory
    // Every time that directory changes we get an event since we want to know which jokes are liked so as to show the appropriate icon
    //
    override fun getFavoriteJokes(userId: String, onResult: (List<Joke>) -> Unit) {
        database
            .reference
            .child(KEY_USER)
            .child(userId)
            .child(KEY_FAVORITE)
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) = onResult(listOf())

                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.run {
                        val jokes = children.mapNotNull {
                            it.getValue(JokeResponse::class.java)
                        }
                        onResult(jokes.map(JokeResponse::mapToJoke))
                    }
                }
            })
    }

    //To change the favourite status
    // we first check if the joke is a user's favourite and remove it if it is already there or add it if it isn't
    // by listening for the value of a child you can tell that the child doesn't exist if the value returned is null
    override fun changeJokeFavoriteStatus(joke: Joke, userId: String) {
        val reference = database.reference
            .child(KEY_USER)
            .child(userId)
            .child(KEY_FAVORITE)
            .child(joke.id)

        reference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val oldJoke = snapshot.getValue(JokeResponse::class.java)

                if (oldJoke != null) {
                    reference.setValue(null)
                } else {
                    reference.setValue(joke)
                }
            }
        })
    }

    // To look up for the profile , we have to enter the users directory the user's id directory to get a specific user
    // since lists are actually "HasMaps" in Firebase, we have to manually parse each item by mapping all children
    override fun getProfile(id: String, onResult: (User) -> Unit) {
        database
            .reference
            .child(KEY_USER)
            .child(id)
            .addValueEventListener(object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) = Unit

                override fun onDataChange(snapshot: DataSnapshot) {
                    val user = snapshot.getValue(UserResponse::class.java)
                    val favoriteJokes = snapshot.child(KEY_FAVORITE)
                        .children
                        .map {
                            it?.getValue(JokeResponse::class.java)
                        }
                        .mapNotNull {
                            it?.mapToJoke()
                        }
                    user?.run {
                        onResult(User(id, username, email, favoriteJokes))
                    }
                }
            })
    }
}