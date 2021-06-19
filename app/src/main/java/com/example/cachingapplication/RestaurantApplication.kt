package com.example.cachingapplication

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//kick-off di process for whole app
@HiltAndroidApp
class RestaurantApplication : Application() {
}