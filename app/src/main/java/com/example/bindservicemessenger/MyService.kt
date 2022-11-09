package com.example.bindservicemessenger

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.Message
import android.os.Messenger
import android.util.Log

class MyService : Service() {
    lateinit var messenger: Messenger

    //handler ** 안드로이드 오에스로 / 오버라이딩
    internal class IncomingHandler(context: Context, val applicationContext: Context = context.applicationContext):Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            when(msg.what){
                10 ->{
                    Log.d("bindservicemessenger", "${msg.what} = ${msg.obj}")
                }
                20 -> {
                    Log.d("bindservicemessenger", "${msg.what} = ${msg.obj}")
                }
                else ->{
                    super.handleMessage(msg)
                }
            }
        }

    }


    override fun onCreate() {

        super.onCreate()
        Log.d("bindservicemessenger", "서비스 onCreate 처리됨")
    }

    override fun onBind(intent: Intent): IBinder {
       messenger = Messenger(IncomingHandler(this))
        return messenger.binder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}