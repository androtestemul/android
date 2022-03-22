package com.apska.lifecycle_state.ui.screens

import android.os.Bundle
import android.util.Log
import com.apska.lifecycle_state.R
import com.apska.lifecycle_state.databinding.ActivityMainBinding
import com.apska.lifecycle_state.ui.BaseActivity

class CounterActivity : BaseActivity<ActivityMainBinding>() {

    companion object {
        private const val TAG = "MainScreen"
        private const val STATE_COUNTER = "StateCounter"

        var isIncreaseEnabled = true
    }

    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding.goToAnotherActivityButton.apply {
            text = getText(R.string.go_to_second_activity)
            setOnClickListener {
                startActivity(PowActivity.getIntentExtraCounter(this@CounterActivity, counter))
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //Log.d(TAG, "onSaveInstanceState")
        outState.putInt(STATE_COUNTER, counter)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        //Log.d(TAG, "onRestoreInstanceState")

        counter = savedInstanceState.getInt(STATE_COUNTER)

        if (isIncreaseEnabled) {
            counter += 1
        } else {
            isIncreaseEnabled = true
        }

    }

    override fun onResume() {
        super.onResume()
        binding.counterTextView.text = counter.toString()
    }

    override fun setBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun getLifecycleOserverTag() = TAG

}