package com.lww.mwwm.model

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var stext = MutableLiveData("Hello World!")
    var num=1
    fun onClickTextView1(view:View){
        num++
        var newtext = "Hello World!" + num
        stext.postValue(newtext)
    }
}
