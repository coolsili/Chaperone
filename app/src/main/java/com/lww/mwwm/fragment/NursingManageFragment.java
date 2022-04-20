package com.lww.mwwm.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.lww.lwwlibrary.activity.BaseFragment;
import com.lww.mwwm.R;
import com.lww.mwwm.databinding.FragmentNursingBinding;
import com.lww.mwwm.entity.MessageEvent;
import com.lww.mwwm.model.NusingViewModel;
import com.lww.mwwm.model.PatientViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class NursingManageFragment extends BaseFragment<FragmentNursingBinding, NusingViewModel> {

    @Override
    protected int getLayout() {
        return R.layout.fragment_nursing;
    }

    @Override
    protected void afterCreate() {
        mViewDataBind.setViewModel(vm);
    }

    @Override
    protected NusingViewModel createViewModel() {
        return new ViewModelProvider(this).get(NusingViewModel.class);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        /* Do something */
        vm.content.setValue(event.content);
    }
}
