package com.apska.lifecycle_state.ui.screens

import android.os.Bundle
import android.util.Log
import com.apska.lifecycle_state.R
import com.apska.lifecycle_state.databinding.ActivityMainBinding
import com.apska.lifecycle_state.ui.BaseActivity

class CounterActivity : BaseActivity<ActivityMainBinding>() {

    private val TAG = "MainScreen"
    private val STATE_IS_INCREASE_ENABLED = "isIncreaseEnabled"
    private val STATE_COUNTER = "StateCounter"

    private var counter = 0
    private var isIncreaseEnabled = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate: counter = $counter")

        binding.apply {
            //counterTextView.text = counter.toString()
            goToAnotherActivityButton.apply {
                text = getText(R.string.go_to_second_activity)
                setOnClickListener {
                    startActivity(PowActivity.getIntentExtraCounter(this@CounterActivity, counter))
                    isIncreaseEnabled = false
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(STATE_COUNTER, counter)
        outState.putBoolean(STATE_IS_INCREASE_ENABLED, isIncreaseEnabled)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        savedInstanceState.apply {
            isIncreaseEnabled = getBoolean(STATE_IS_INCREASE_ENABLED)
            counter = getInt(STATE_COUNTER)
        }

        Log.d(TAG, "onRestoreInstanceState: State isIncreaseEnabled = $isIncreaseEnabled")

        if (isIncreaseEnabled) {
            counter += 1
        } else {
            isIncreaseEnabled = true
        }

        Log.d(TAG, "onRestoreInstanceState: out isIncreaseEnabled = $isIncreaseEnabled")
        Log.d(TAG, "onRestoreInstanceState: out counter = $counter")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: counter = $counter")
        binding.counterTextView.text = counter.toString()
    }

    override fun setBinding() = ActivityMainBinding.inflate(layoutInflater)

}