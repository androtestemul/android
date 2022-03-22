package com.apska.lifecycle_state.ui.screens

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.apska.lifecycle_state.R
import com.apska.lifecycle_state.databinding.ActivityMainBinding
import com.apska.lifecycle_state.ui.BaseActivity

class PowActivity : BaseActivity<ActivityMainBinding>() {

    companion object {
        private const val TAG = "SecondScreen"
        private const val STATE_COUNTER = "StateCounter"
        private const val STATE_CHANGE_CONFIG_COUNTER = "StateChangeConfigCounter"
        private const val EXTRA_COUNTER = "ExtraCounter"

        fun getIntentExtraCounter(context: Context, counter: Int): Intent {
            return Intent(context, PowActivity::class.java).apply {
                putExtra(EXTRA_COUNTER, counter)
            }
        }
    }

    private var changeConfigCounter = 0
    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        outState.putInt(STATE_CHANGE_CONFIG_COUNTER, changeConfigCounter)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        //была смена конфигурации
        changeConfigCounter = savedInstanceState.getInt(STATE_CHANGE_CONFIG_COUNTER)

        changeConfigCounter += 1

        //Если конфигурация сменилась (в расчет берем только поворот экрана) нечетное
        //количество раз, то значит экран первой активити тоже изменился и пересоздастся
        CounterActivity.isIncreaseEnabled = changeConfigCounter%2 != 1
    }

    override fun setBinding() = ActivityMainBinding.inflate(layoutInflater)

    override fun getLifecycleOserverTag() = TAG
}