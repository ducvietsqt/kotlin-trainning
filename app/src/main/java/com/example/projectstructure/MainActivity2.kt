package com.example.projectstructure

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projectstructure.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private  lateinit var binding: ActivityMain2Binding
    // todo: declare receiver
    private val timerReceiver = TimerReceiver(::onTimerReceiveTick, ::onTimerReceiveFinished)

    private fun onTimerReceiveTick(ms:Long) {
        print("TFH_onTimerReceive " + ms)
        binding.text1.text = "Screen 2 Second(s)" + (ms / 1000).toString()
    }

    private fun onTimerReceiveFinished() {
        binding.text1.text = "SUCCESS SCREEN 2"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // todo: init TimerService
        var intent = Intent(this, TimerService::class.java)
        // todo: start service call onStartCommand
        startService(intent)
    }

    override fun onResume() {
        super.onResume()
        // todo: register receiver
        registerReceiver(timerReceiver, IntentFilter(TimerService.ACTION_TICK))
        registerReceiver(timerReceiver, IntentFilter(TimerService.ACTION_FINISHED))
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(timerReceiver)
    }

}

