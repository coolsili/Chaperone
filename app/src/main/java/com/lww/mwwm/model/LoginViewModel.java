package com.lww.mwwm.model;

import android.app.Activity;
import android.content.ContextWrapper;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lww.mwwm.activity.HomeActivity;
import com.lww.mwwm.entity.UserInfo;
import com.lww.mwwm.retrofit.BaseObserver;
import com.lww.mwwm.retrofit.ObservableManager;
import com.lww.mwwm.retrofit.ParamsBuilder;
import com.lww.mwwm.retrofit.RetrofitHandler;
import com.lww.mwwm.retrofit.RxTransformerHelper;
import com.lww.mwwm.retrofit.entity.BaseResponseEntity;

import io.reactivex.Observable;
import io.reactivex.Observer;


public class LoginViewModel extends ViewModel{
    public MutableLiveData<String> userName = new MutableLiveData<>("18511284125");
    public MutableLiveData<String> password = new MutableLiveData<>("sinomis2028");
    public MutableLiveData<UserInfo> userInfo = new MutableLiveData<>(new UserInfo());

    public void onLogin(View view){
        //判断限制输入字符是否正确，是否有网
//        Toast.makeText(BaseApplication.getInstance(),userName+" "+password, Toast.LENGTH_SHORT).show();

        RetrofitHandler.getInstance().getAPIService()
                .login(ObservableManager.getInstance().getRequestBody(ParamsBuilder.getIntance()
                        .addParams("userName",userName.getValue())
                        .addParams("password",password.getValue())
                        .addParams("type","0").get()))
                .compose(RxTransformerHelper.observableIO2Main())
                .subscribe(new BaseObserver<UserInfo>() {
                    @Override
                    protected void onSuccess(BaseResponseEntity<UserInfo> tBaseEntity) {
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
        RetrofitHandler.getInstance().getAPIService()
                .logOut(UserInfo.class,ObservableManager.getInstance().getRequestBody(ParamsBuilder.getIntance()
                        .addParams("userName",userName.getValue())
                        .addParams("password",password.getValue())
                        .addParams("type","0").get()))
                .compose(RxTransformerHelper.<BaseResponseEntity<UserInfo>>observableIO2Main())
                .subscribe(new BaseObserver<UserInfo>() {
                    @Override
                    protected void onSuccess(BaseResponseEntity<UserInfo> tBaseEntity) {
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
