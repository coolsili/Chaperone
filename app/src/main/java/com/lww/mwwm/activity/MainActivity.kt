package com.lww.mwwm.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil.setContentView
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.lww.lwwlibrary.activity.BaseActivity
import com.lww.mwwm.R
import com.lww.mwwm.databinding.ActivityMainBinding
import com.lww.mwwm.model.MainViewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override fun createViewModel(): MainViewModel {
        return ViewModelProvider(this).get(MainViewModel::class.java!!)
    }

    override fun afterCreate() {
        mViewDataBind.viewModel = vm
    }

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
    }
}
