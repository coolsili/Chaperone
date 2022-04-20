package com.lww.lwwlibrary.retrofit;
import com.lww.lwwlibrary.entity.BaseEntity;
import com.lww.lwwlibrary.retrofit.entity.BaseResponseEntity;
import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 日期 2020/04/13 10:07
 * lww
 * 自己构建ApiService类
 */
public interface ApiService {

    //登录
    @POST("appDisUserLoginController.do?toUserLogin")
    Observable<BaseResponseEntity<BaseEntity>> login(@Body RequestBody requestBody);

    @POST("appDisUserLoginController.do?toUserLogin")
    Observable<BaseResponseEntity<BaseEntity>> logOut(@Body RequestBody requestBody);
}
