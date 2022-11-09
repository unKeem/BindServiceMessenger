package com.example.bindservicemessenger

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.example.bindservicemessenger.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
     var messenger: Messenger?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val connection: ServiceConnection = object:ServiceConnection{
            override fun onServiceConnected(p0: ComponentName?, iBinder: IBinder?) {
                 messenger = Messenger(iBinder)
                binding.btnMessenger.isEnabled = true
                Log.d("bindservicemessenger", "messenger(iBinder) 객체받음")
            }

            override fun onServiceDisconnected(p0: ComponentName?) {
                Log.d("bindservicemessenger", "messenger(iBinder) 객체받지 못함")
            }
        }
        binding.btnBindService.setOnClickListener{
            val intent = Intent(this, MyService::class.java)
            bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
        binding.btnMessenger.setOnClickListener{
            if(messenger !=null){
                val message = Message()
                message.what = 10
                message.obj = "경<<<취업>>>축"
                messenger?.send(message)

            }
        }
    }
}