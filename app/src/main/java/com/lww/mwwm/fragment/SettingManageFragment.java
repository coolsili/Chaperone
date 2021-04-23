package com.lww.mwwm.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.lww.mwwm.R;
import com.lww.mwwm.databinding.FragmentSettingBinding;
import com.lww.mwwm.model.SettingViewModel;

public class SettingManageFragment extends Fragment {
    private FragmentSettingBinding mBinding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_setting,
                container,
                false);
        return mBinding.getRoot();

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        SettingViewModel viewModel = viewModelProvider.get(SettingViewModel.class);
        mBinding.setViewModel(viewModel);
        //数据改变，UI自动会更新
        mBinding.setLifecycleOwner(this);
    }
}
