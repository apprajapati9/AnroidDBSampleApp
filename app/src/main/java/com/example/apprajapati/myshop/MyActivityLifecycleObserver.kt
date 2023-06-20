package com.example.apprajapati.myshop

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

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

class MyActivityLifecycleObserver : DefaultLifecycleObserver {

    companion object{
        private val LOG_TAG = MyActivityLifecycleObserver::class.simpleName
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        Log.i(LOG_TAG, "On Start" )
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        Log.i(LOG_TAG, "On Resume" )
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        Log.i(LOG_TAG, "On Stop" )
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        Log.i(LOG_TAG, "On Destroy" )
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        Log.i(LOG_TAG, "On Pause" )
    }
}