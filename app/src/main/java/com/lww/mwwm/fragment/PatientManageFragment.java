package com.lww.mwwm.fragment;

import androidx.lifecycle.ViewModelProvider;

import com.lww.lwwlibrary.activity.BaseFragment;
import com.lww.mwwm.R;
import com.lww.mwwm.databinding.FragmentPatientBinding;
import com.lww.mwwm.model.PatientViewModel;

public class PatientManageFragment extends BaseFragment<FragmentPatientBinding, PatientViewModel> {

    @Override
    protected int getLayout() {
        return R.layout.fragment_patient;
    }

    @Override
    protected PatientViewModel createViewModel() {
        return new ViewModelProvider(this).get(PatientViewModel.class);
    }

    @Override
    protected void afterCreate() {
        mViewDataBind.setViewModel(vm);
    }
}
