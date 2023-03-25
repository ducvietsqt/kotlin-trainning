package com.example.projectstructure

import android.app.AlertDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.example.api.Api
import com.example.projectstructure.databinding.ActivityLoginBinding
import com.example.projectstructure.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import okhttp3.Dispatcher

class Login : BaseActivity() {
    private lateinit var binding: ActivityLoginBinding
    var job: Job? = null
    var expction = CoroutineExceptionHandler{ _, throwable -> Log.d("TAG", "${throwable.localizedMessage}")}
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnSubmit.setOnClickListener{
            binding.loaderTemplate.visibility = View.VISIBLE
            login("viet@mailinator.com", "123456@aA")
        }
    }

    fun login(userName: String, password: String) {
        // todo loading => base view model (loading, disabled loading,...)

        job = CoroutineScope(Dispatchers.IO + expction).launch {
            var response = MainApplication().retrofitService?.login(userName, password)
            withContext(Dispatchers.Main) {
                if(response?.isSuccessful == true) {
                    // todo hide loading
                    // todo live data -> update data user global

                }else {
                    // todo error


                }
            }
        }

    }



}