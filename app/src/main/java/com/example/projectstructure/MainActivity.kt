package com.example.projectstructure

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.projectstructure.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // todo: init layout id
    private lateinit var binding: ActivityMainBinding
    // todo: declare receiver
    private val timerReceiver = TimerReceiver(::onTimerReceiveTick, ::onTimerReceiveFinished)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // todo: init TimerService
        var intent = Intent(this, TimerService::class.java)
        // todo: start service call onStartCommand
        startService(intent)
    }

    private fun onTimerReceiveTick(ms:Long) {
        print("TFH_onTimerReceive " + ms)
        binding.text1.text = "Screen 1 Second(s)" + (ms / 1000).toString()
    }

    private fun onTimerReceiveFinished() {
        binding.text1.text = "SUCCESS SCREEN 1"
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        // todo: register receiver
        registerReceiver(timerReceiver, IntentFilter(TimerService.ACTION_TICK))
        registerReceiver(timerReceiver, IntentFilter(TimerService.ACTION_FINISHED))

        binding.btn1.setOnClickListener {
            var intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // todo: un-register receiver
        unregisterReceiver(timerReceiver)
    }

}

class TimerReceiver(val onTick: (ms:Long) -> Unit, val onFinished: () -> Unit) : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if(intent == null) return
        when(intent.action) {
            TimerService.ACTION_TICK -> {
                var data = intent.getLongExtra("ON_TICK_TIMER", 0)
                onTick(data)
            }
            TimerService.ACTION_FINISHED -> {
                onFinished()
            }
        }
    }

}