package com.lww.lwwlibrary.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;

/**
 *  2021-04-26 15:11
 *  lww
 * @param <V>
 * @param <VM>
 */
public abstract class BaseFragment<V extends ViewDataBinding, VM extends ViewModel> extends Fragment {
    protected V mViewDataBind;
    protected VM vm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mViewDataBind = DataBindingUtil.inflate(inflater,
                getLayout(),
                container,
                false);
        return mViewDataBind.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewDataBind.setLifecycleOwner(this);
        vm = createViewModel();
        afterCreate();
    }

    protected abstract int getLayout();
    protected abstract VM createViewModel();
    protected abstract void afterCreate();


}
