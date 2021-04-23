package com.lww.lwwlibrary.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;


public abstract class BaseActivity<V extends ViewDataBinding, VM extends ViewModel> extends AppCompatActivity {
    protected V mViewDataBind;
    protected VM vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewDataBind = DataBindingUtil.setContentView(this, getLayout());
        mViewDataBind.setLifecycleOwner(this);
        vm = createViewModel();
        afterCreate();
    }
    protected abstract int getLayout();
    protected abstract void afterCreate();
    protected abstract VM createViewModel();


}

