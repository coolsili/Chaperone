package com.lww.mwwm.model;


import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lww.mwwm.entity.MessageEvent;

import org.greenrobot.eventbus.EventBus;


public class PatientViewModel extends ViewModel{
    public MutableLiveData<String> content = new MutableLiveData<>("PatientViewMode");
    public int i = 0;

    //构造初始化数据
    public PatientViewModel(){

    }

    public void onButtonClick(){
        Log.e("TAG",this.content.getValue());
        this.content.setValue("病患管理"+ i++);
        EventBus.getDefault().post(new MessageEvent(this.content.getValue()));

    }
    public void onButton2Click(){

    }

}
