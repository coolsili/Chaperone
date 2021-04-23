package com.lww.mwwm.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.lww.mwwm.model.BaseViewModel;


public abstract  class BaseAppCompatActivity <V extends ViewDataBinding, VM extends BaseViewModel> extends RxAppCompatActivity {
    protected V binding;
    protected VM viewModel;
    private int viewModelId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * 初始化根布局
     *
     * @return 布局layout的id
     */
    public abstract int initContentView(Bundle savedInstanceState);


}
