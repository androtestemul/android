package com.apska.lifecycle_state.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    protected lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycle.addObserver(MyLifecycleObserver(getLifecycleOserverTag()))

        binding = setBinding()

        setContentView(binding.root)
    }

    abstract fun setBinding(): VB

    abstract fun getLifecycleOserverTag(): String

}