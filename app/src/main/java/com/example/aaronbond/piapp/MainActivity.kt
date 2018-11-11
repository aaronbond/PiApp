package com.example.aaronbond.piapp

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.aaronbond.piapp.databinding.ActivityMainBinding
import com.example.aaronbond.piapp.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private var isBound = false

    lateinit var serviceBinder: PiService.ServiceBinder
    val mainViewModel = MainViewModel()

    private val connection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            Log.i("Service", "Service disconnected")
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            serviceBinder = service as PiService.ServiceBinder
            mainViewModel.setServiceBinder(serviceBinder)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Intent(this, PiService::class.java).apply {
            isBound = bindService(this, connection, Context.BIND_AUTO_CREATE)
        }

        val binding: ActivityMainBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_main
        )

        binding.viewModel = mainViewModel
    }

    override fun onDestroy() {
        if(isBound) unbindService(connection)
        super.onDestroy()
    }
}
