package com.lww.mwwm.model;

import android.app.Activity;
import android.content.ContextWrapper;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lww.lwwlibrary.entity.BaseEntity;
import com.lww.lwwlibrary.retrofit.ApiService;
import com.lww.lwwlibrary.retrofit.BaseObserver;
import com.lww.lwwlibrary.retrofit.ObservableManager;
import com.lww.lwwlibrary.retrofit.ParamsBuilder;
import com.lww.lwwlibrary.retrofit.RetrofitHandler;
import com.lww.lwwlibrary.retrofit.RxTransformerHelper;
import com.lww.lwwlibrary.retrofit.entity.BaseResponseEntity;
import com.lww.mwwm.activity.HomeActivity;
import com.lww.mwwm.entity.UserInfo;


public class LoginViewModel extends ViewModel{
    public MutableLiveData<String> userName = new MutableLiveData<>("18511284125");
    public MutableLiveData<String> password = new MutableLiveData<>("sinomis2028");
    public MutableLiveData<UserInfo> userInfo = new MutableLiveData<>(new UserInfo());

    public void onLogin(View view){
        //判断限制输入字符是否正确，是否有网

//        Toast.makeText(BaseApplication.getInstance(),userName+" "+password, Toast.LENGTH_SHORT).show();
        RetrofitHandler.getInstance().getAPIService(ApiService.class)
                .login(ObservableManager.getInstance().getRequestBody(ParamsBuilder.getIntance()
                        .addParams("instrumentId",userName.getValue())
                        .addParams("monitorItemId",userName.getValue())
                        .addParams("pageSize",20)
                        .addParams("currentPage",1)
                        .addParams("startTime",password.getValue())
                        .addParams("endTime",password.getValue())
                        .addParams("status","1").get()))
                .compose(RxTransformerHelper.<BaseResponseEntity<BaseEntity>>observableIO2Main())
                .subscribe(new BaseObserver<BaseEntity>() {
                    @Override
                    protected void onSuccess(BaseResponseEntity<BaseEntity> tBaseEntity) {
                        if(view.getContext() instanceof Activity){
                            view.getContext().startActivity(new Intent(view.getContext(), HomeActivity.class));
                            return;
                        }
                        if(view.getContext() instanceof ContextWrapper){
                            ((Activity)(view.getContext())).startActivity(new Intent(view.getContext(), HomeActivity.class));
                        }
                    }

                    @Override
                    protected void onFailure(String errorMessage) {
                        Log.e("TAG", "onFailure time:" + errorMessage);
                        Toast.makeText(view.getContext(),errorMessage, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void onLogin2(View view){
        //判断限制输入字符是否正确，是否有网
//        Toast.makeText(BaseApplication.getInstance(),userName+" "+password, Toast.LENGTH_SHORT).show();
        RetrofitHandler.getInstance().getAPIService(ApiService.class)
                .logOut(ObservableManager.getInstance().getRequestBody(ParamsBuilder.getIntance()
                        .addParams("userName",userName.getValue())
                        .addParams("password",password.getValue())
                        .addParams("type","0").get()))
                .compose(RxTransformerHelper.<BaseResponseEntity<BaseEntity>>observableIO2Main())
                .subscribe(new BaseObserver<BaseEntity>() {
                    @Override
                    protected void onSuccess(BaseResponseEntity<BaseEntity> tBaseEntity) {
                        if(view.getContext() instanceof Activity){
                            view.getContext().startActivity(new Intent(view.getContext(), HomeActivity.class));
                            return;
                        }
                        if(view.getContext() instanceof ContextWrapper){
                            ((Activity)(view.getContext())).startActivity(new Intent(view.getContext(), HomeActivity.class));
                        }
                    }

                    @Override
                    protected void onFailure(String errorMessage) {
                        Log.e("TAG", "onFailure time:" + errorMessage);
                    }
                });
    }

}
