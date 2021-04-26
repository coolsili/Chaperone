package com.lww.mwwm.activity;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.lww.mwwm.R;
import com.lww.mwwm.databinding.ActivityRecyclerViewBinding;
import com.lww.mwwm.model.RecyclerViewListViewModel;
import com.lww.lwwlibrary.activity.BaseActivity;

public class RecyclerViewActivity extends BaseActivity<ActivityRecyclerViewBinding,RecyclerViewListViewModel> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_recycler_view);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_recycler_view;
    }

    @Override
    protected void afterCreate() {

    }

    @Override
    protected RecyclerViewListViewModel createViewModel() {
        return new ViewModelProvider(this).get(RecyclerViewListViewModel.class);
    }
}
