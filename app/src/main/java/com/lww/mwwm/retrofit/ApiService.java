package com.lww.mwwm.retrofit;

import com.lww.mwwm.retrofit.entity.BaseResponseEntity;
import com.lww.mwwm.entity.UserInfo;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 日期 2020/04/13 10:07
 * lww
 */
public interface ApiService {
    String APP_SERVER_BASE_URL = "https://iomstest.logimis.com/";

    //登录
    @POST("appDisUserLoginController.do?toUserLogin")
    Observable<BaseResponseEntity<UserInfo>> login(@Body RequestBody requestBody);

    //登录
    @POST("appDisUserLoginController.do?toUserLogin")
    <T> Observable<BaseResponseEntity<T>> logOut(Class<T> t,@Body RequestBody requestBody);

}
