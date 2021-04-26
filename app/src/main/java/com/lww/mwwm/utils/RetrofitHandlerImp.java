package com.lww.mwwm.utils;

import com.lww.lwwlibrary.retrofit.ApiService;
import com.lww.lwwlibrary.retrofit.RetrofitHandler;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 2021-04-26 16:18
 * lww
 */
public class RetrofitHandlerImp extends RetrofitHandler<ApiService> {
    private static RetrofitHandlerImp mRetrofitHandlerImp;
    private RetrofitHandlerImp() {
        initRetrofit();
    }

    public static synchronized RetrofitHandlerImp getInstance() {
        if (mRetrofitHandlerImp == null) {
            synchronized (RetrofitHandlerImp.class) {
                if (mRetrofitHandlerImp == null) {
                    mRetrofitHandlerImp = new RetrofitHandlerImp();
                }
            }
        }
        return mRetrofitHandlerImp;
    }

    @Override
    protected void initRetrofit() {
        super.initRetrofit();
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(ApiService.APP_SERVER_BASE_URL)
                //JSON转换器,使用Gson来转换
                .addConverterFactory(GsonConverterFactory.create())
                //RxJava适配器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mOkHttpClient)
                .build();
        t = mRetrofit.create(ApiService.class);
    }
}
