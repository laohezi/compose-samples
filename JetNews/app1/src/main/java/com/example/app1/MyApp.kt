package com.example.app1

import android.app.Application

lateinit  var appContext:Application

class MyApp :Application(){
    override fun onCreate() {
        super.onCreate()
        appContext = this
    }
}