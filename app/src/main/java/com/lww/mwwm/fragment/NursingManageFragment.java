package com.lww.mwwm.fragment;

import androidx.lifecycle.ViewModelProvider;

import com.lww.lwwlibrary.activity.BaseFragment;
import com.lww.mwwm.R;
import com.lww.mwwm.databinding.FragmentNursingBinding;
import com.lww.mwwm.model.NusingViewModel;

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
}
