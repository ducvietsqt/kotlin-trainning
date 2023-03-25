package com.example.projectstructure

import android.app.Application
import com.example.api.Api
import com.example.api.IApi

class MainApplication : Application() {
    var retrofitService:IApi? = null
   init {
       retrofitService = Api.getInstance()
   }

}
