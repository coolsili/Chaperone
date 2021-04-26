package com.lww.mwwm.model;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lww.mwwm.entity.MessageEvent;

import org.greenrobot.eventbus.EventBus;


public class NusingViewModel extends ViewModel{
    public MutableLiveData<String> content =new MutableLiveData<>("NusingManage");
    public int i = 0;

    public void onButtonClick(){
        Log.e("TAG",this.content.getValue());
        this.content.setValue("什么管理"+ i++);

    }
}
