package com.lww.mwwm.activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.math.MathUtils;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.lww.mwwm.R;
import com.lww.mwwm.databinding.ActivityHomeBinding;
import com.lww.mwwm.entity.TabItem;
import com.lww.mwwm.fragment.NursingManageFragment;
import com.lww.mwwm.fragment.PatientManageFragment;
import com.lww.mwwm.fragment.SettingManageFragment;
import com.lww.mwwm.model.HomeViewModel;

import java.util.ArrayList;

public class HomeActivity extends BaseActivity<ActivityHomeBinding,HomeViewModel> {

    @Override
    protected HomeViewModel createViewModel() {
        return new ViewModelProvider(this).get(HomeViewModel.class);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
//        HomeViewModel homeViewModel = viewModelProvider.get(HomeViewModel.class);
//        binding.setViewModel(homeViewModel);
//        binding.setLifecycleOwner(this);

        final PatientManageFragment patientFragment = new PatientManageFragment();
        final NursingManageFragment nursingFragment = new NursingManageFragment();
        final SettingManageFragment settingFragment = new SettingManageFragment();
        final FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            private Fragment[] fragments = new Fragment[]{
                    patientFragment,
                    nursingFragment,
                    settingFragment,
            };

            @Override
            public int getCount() {
                return fragments.length;
            }

            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments[position];
            }
        };
        mViewDataBind.pages.setAdapter(adapter);
        mViewDataBind.pages.setOffscreenPageLimit(3);

        mViewDataBind.pages.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                mViewDataBind.tabs.setCurrentTab(position);
            }
        });

        mViewDataBind.tabs.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewDataBind.pages.setCurrentItem(MathUtils.clamp(
                        position,
                        0,
                        adapter.getCount()));
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        ArrayList<CustomTabEntity> list = new ArrayList<>();
        list.add(new TabItem(getString(R.string.patient), R.drawable.ic_launcher_background, R.drawable.ic_launcher_background));
        list.add(new TabItem(getString(R.string.nusing), R.drawable.ic_launcher_background, R.drawable.ic_launcher_background));
        list.add(new TabItem(getString(R.string.setting), R.drawable.ic_launcher_background, R.drawable.ic_launcher_background));
        mViewDataBind.tabs.setTabData(list);

    }

    @Override
    protected void afterCreate() {
//        vm =
        mViewDataBind.setViewModel(vm);

    }


}
