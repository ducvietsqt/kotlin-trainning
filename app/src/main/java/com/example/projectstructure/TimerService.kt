package com.example.projectstructure

import android.app.Service
import android.content.Intent
import android.os.CountDownTimer
import android.os.IBinder

class TimerService : Service() {
    companion object {
        const val ACTION_FINISHED : String = "your.pkg.name.ACTION_FINISHED"
        const val ACTION_TICK : String = "your.pkg.name.ACTION_TICK"
    }
    private lateinit var timer : CountDownTimer
    private var finishIntent = Intent(ACTION_FINISHED)
    private var tickIntent = Intent(ACTION_TICK)


    override fun onBind(intent: Intent?): IBinder? {
        return null;
    }

    override fun onCreate() {
        super.onCreate()
        timer = createTimer(100000)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        timer.start()
        return START_NOT_STICKY // TODO: stop on background
        //        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()

    }

    fun createTimer(time: Long) : CountDownTimer = object : CountDownTimer(time, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            tickIntent.putExtra("ON_TICK_TIMER", millisUntilFinished)
            sendBroadcast(tickIntent)
        }

        override fun onFinish() {
            sendBroadcast(finishIntent)
        }

    }


}