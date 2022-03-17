package com.example.quoteandjet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.quoteandjet.Observers.LifeCycleObserverClass

class MainActivity : AppCompatActivity() {

    private var TAG = "Lifecycler"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycle.addObserver(LifeCycleObserverClass())
        Log.e(TAG, "onCreate: Executed", )
    }

    override fun onStart() {
        super.onStart()
        Log.e(TAG, "onStart: Executed", )
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume: Executed", )
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG, "onPause: Executed", )
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, "onStop: Executed", )
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy: Executed", )
    }
}