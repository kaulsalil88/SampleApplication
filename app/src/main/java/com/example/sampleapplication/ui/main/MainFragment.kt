package com.example.sampleapplication.ui.main

import android.gesture.Gesture
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.GestureDetectorCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sampleapplication.R

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var mGestureDetector: GestureDetectorCompat

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mGestureDetector = GestureDetectorCompat(view.context, MyGestureListener())
        view.setOnTouchListener { v, event ->
            if (mGestureDetector.onTouchEvent(event)) {
                true
            } else {
                false
            }
        }
        view.findViewById<TextView>(R.id.message).setOnClickListener { Toast.makeText(it.context, "Clicked", Toast.LENGTH_SHORT).show() }
    }


    private class MyGestureListener : GestureDetector.SimpleOnGestureListener() {
        val DEBUG_TAG = "MyGestureListener"
        private val SWIPE_THRESHOLD = 100
        private val SWIPE_VELOCITY_THRESHOLD = 100

        override fun onDown(event: MotionEvent): Boolean {
            Log.d(DEBUG_TAG, "onDown: $event")
            return true
        }

        override fun onFling(
                event1: MotionEvent,
                event2: MotionEvent,
                velocityX: Float,
                velocityY: Float
        ): Boolean {
            Log.d(DEBUG_TAG, "onFling: $event1 $event2")
            var result = false
            try {
                val diffY = event2.y - event1.y
                val diffX = event2.x - event1.x
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
//                            Add Profile To Db
                            Log.d(DEBUG_TAG, "Righ Swipe")
                        } else {
                            //Fetch New Data from API to Display
                            Log.d(DEBUG_TAG, "Left Swipe")
                        }
                        result = true
                    }
                }

            } catch (exception: Exception) {
                exception.printStackTrace()
            }

            return result


        }
    }

}