package com.apska.lifecycle_state.ui.screens

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.apska.lifecycle_state.R
import com.apska.lifecycle_state.databinding.ActivityMainBinding
import com.apska.lifecycle_state.ui.BaseActivity

class PowActivity : BaseActivity<ActivityMainBinding>() {

    private val TAG = "SecondScreen"
    private val STATE_COUNTER = "StateCounter"

    private var counter = 0

    companion object {
        private const val EXTRA_COUNTER = "ExtraCounter"

        fun getIntentExtraCounter(context: Context, counter: Int): Intent {
            return Intent(context, PowActivity::class.java).apply {
                putExtra(EXTRA_COUNTER, counter)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate: ${intent.getIntExtra(EXTRA_COUNTER, -1)}")

        counter = intent.getIntExtra(EXTRA_COUNTER, 0)

        counter *= counter

        binding.apply {
            counterTextView.text = counter.toString()
            goToAnotherActivityButton.apply {
                text = getText(R.string.go_to_main_activity)
                setOnClickListener {
                    this@PowActivity.finish()
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(STATE_COUNTER, counter)
    }

    override fun setBinding() = ActivityMainBinding.inflate(layoutInflater)
}