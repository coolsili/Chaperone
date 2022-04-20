package com.lww.mwwm.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.lww.mwwm.R;
import com.lww.mwwm.databinding.ActivityLoginBinding;
import com.lww.mwwm.model.LoginViewModel;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        LoginViewModel loginViewModel = viewModelProvider.get(LoginViewModel.class);
        binding.setViewModel(loginViewModel);
        //数据改变，UI自动会更新
        binding.setLifecycleOwner(this);

    }


}
