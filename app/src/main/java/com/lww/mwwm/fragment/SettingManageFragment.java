package com.lww.mwwm.fragment;

import androidx.lifecycle.ViewModelProvider;

import com.lww.lwwlibrary.activity.BaseFragment;
import com.lww.mwwm.R;
import com.lww.mwwm.databinding.FragmentSettingBinding;
import com.lww.mwwm.model.SettingViewModel;

public class SettingManageFragment extends BaseFragment<FragmentSettingBinding,SettingViewModel> {

    @Override
    protected int getLayout() {
        return R.layout.fragment_setting;
    }

    @Override
    protected void afterCreate() {
        mViewDataBind.setViewModel(vm);
    }

    @Override
    protected SettingViewModel createViewModel() {
        return new ViewModelProvider(this).get(SettingViewModel.class);
    }
}
