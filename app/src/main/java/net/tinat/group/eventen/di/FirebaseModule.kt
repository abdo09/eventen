package net.tinat.group.eventen.di

import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val firebaseModule = module {

    single {
        val eventenAppOptions = FirebaseOptions.Builder()
                .setApplicationId("1:1094205871994:android:a51cecca98291ba61be8c3")
                .setProjectId("chat-eventen-fc946-da32b")
                .setApiKey("AIzaSyC7S6DNkaMNRkESfHRiCRbf1CjPEphsfhk")
                .build()
        FirebaseApp.initializeApp(androidApplication(), eventenAppOptions, "eventen_application")
    }

    //firebase module

    single {
        FirebaseFirestore.getInstance(get())
    }

    single {
        FirebaseAuth.getInstance(get())
    }

}