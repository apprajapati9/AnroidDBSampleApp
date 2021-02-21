package com.example.apprajapati.myshop

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

/*
    USE: many components in Android require you to subscribe or initialize components and unsubscribe or stop
        whenever necessary. If we forget that then it can cause bugs and memory leaks.

        Typically, we can handle these operations on native onResume() and etc such activity lifecycle methods,
        however, code can get messy and complex/difficult to maintain. To handle it better, you can use
        LifeCycle Components android jetpack provides.

        Lifecycle : holds information about lifecycle state of the component and allows other objects to observe that state
        Uses two main enumerations to tack status, Event and State.
         Event -  mapped to the events and activities and fragment events on create, on start, on destory
         state -  holds the current state of the component tracked by lifecycle object, like created, started and destroyed.
        , lifecycleOwner: it is an interface that has a lifecycle.
        , LifecycleObserver : interface. classes that want to observe a lifecycle events would implement this interface.
        and specify which event it wants to observe.


 */

class MyActivityLifecycleObserver : LifecycleObserver {

    companion object{
        private val LOG_TAG = MyActivityLifecycleObserver::class.simpleName
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun connect(){
        Log.i(LOG_TAG, "On Resume" )
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun disconnect(){
        Log.i(LOG_TAG, "On Pause" )
    }
}