package com.example.quoteandjet.Observers

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class LifeCycleObserverClass: LifecycleObserver{

    private var TAG = "Lifecycler"

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun observerOnCreate(){
        Log.e(TAG, "observerOnCreate: Executed")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun observerOnStart(){
        Log.e(TAG, "observerOnStart: Executed", )
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun observerOnResume(){
        Log.e(TAG, "observerOnResume: Executed", )
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun observerOnPause(){
        Log.e(TAG, "observerOnPause: Executed", )
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun observerOnStop(){
        Log.e(TAG, "observerOnStop: Executed", )
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun observerOnDestroy(){
        Log.e(TAG, "observerOnDestroy: Executed", )
    }
}