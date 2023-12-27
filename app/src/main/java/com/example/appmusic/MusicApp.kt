package com.example.appmusic

import android.app.Application
import com.google.firebase.FirebaseApp

class MusicApp:Application(){
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }

}