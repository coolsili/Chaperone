package com.lww.lwwlibrary.retrofit;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author lww
 * @description 拦截器工具类
 */

public class InterceptorHelper {
    public static String TAG = "Interceptor";
    public static boolean isLog = false;

    /**
     * 日志拦截器
     */
    public static HttpLoggingInterceptor getLogInterceptor() {
        return new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                 if(isLog){
                    Log.w(TAG, "LogInterceptor---------: " + message);
                }
            }
        }).setLevel(isLog?HttpLoggingInterceptor.Level.BODY:HttpLoggingInterceptor.Level.NONE);//设置打印数据的级别
    }

    /**
     * 缓存拦截器
     *
     * @return
     */
    public static Interceptor getCacheInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                //CONTEXT不能为空 判断是否有网路连接
//                if (!NetworkUtils.isConnected(PalApplication.getInstance().getApplicationContext())) {
                    int maxStale = 4 * 7 * 24 * 60; // 离线时缓存保存4周,单位:秒
                    CacheControl tempCacheControl = new CacheControl.Builder()
                            .onlyIfCached()
                            .maxStale(maxStale, TimeUnit.SECONDS)
                            .build();
                    request = request.newBuilder()
                            .cacheControl(tempCacheControl)
                            .build();
//                }
                return chain.proceed(request);
            }
        };
    }


    /**
     * 重试拦截器
     *
     * @return
     */
    public static Interceptor getRetryInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                int maxRetry = 2;//最大重试次数
                int retryNum = 0;//假如设置为3次重试的话，则最大可能请求4次（默认1次+3次重试）
                int retryInterval = 500;
                Request request = chain.request();
                Response response = chain.proceed(request); // 初始尝试
                while (!response.isSuccessful() && retryNum < maxRetry) {
                    retryNum++;
                    Log.d("RetryInterceptor", "重试尝试 #" + retryNum + "/" + maxRetry);
                    if (response.body() != null) {
                        response.body().close(); // 关闭之前不成功的响应体很重要
                    }
                    try {
                        Thread.sleep(retryInterval);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    response = chain.proceed(request); // 重试
                }
                return response;
            }
        };
    }

    /**
     * 请求头拦截器
     *
     * @return
     */
    public static Interceptor getHeaderInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                //在这里你可以做一些想做的事,比如token失效时,重新获取token
                //或者添加header等等

                Request originalRequest = chain.request();
                RequestBody body = originalRequest.body();
                // 如果请求体为空，或者已经有编码了，直接跳过
                    if (body == null || originalRequest.header("Content-Encoding") != null) {
                        return chain.proceed(originalRequest);
                    }
                    
                    // !! 关键的判断 !!
                    // 如果请求的 Content-Type 是 multipart，则不进行 Gzip 压缩
                    MediaType contentType = body.contentType();
                    if (contentType != null && "multipart".equals(contentType.type())) {
                        return chain.proceed(originalRequest);
                    }
                if (null == originalRequest.body()) {
                    return chain.proceed(originalRequest);
                }
                Request compressedRequest = originalRequest.newBuilder()
                        .addHeader("Content-Encoding", "gzip")
                        .addHeader("User-Agent", "OkHttp Headers.java")
//                        .addHeader("Accept", "application/json; q=0.5")
//                        .addHeader("Accept", "application/vnd.github.v3+json")
                        .addHeader("Accept-Encoding", "identity")

                        .addHeader("Accept-Encoding", "gzip")
                        .addHeader("Accept", "application/json")
//                        .addHeader("Content-Type", "application/json; charset=utf-8")
                        .method(originalRequest.method(), originalRequest.body())
//                    .addHeader(Constants.WEB_TOKEN, webi_token)
                        .build();
                Response proceed = chain.proceed(compressedRequest);
                return proceed;
            }
        };

    }
}

