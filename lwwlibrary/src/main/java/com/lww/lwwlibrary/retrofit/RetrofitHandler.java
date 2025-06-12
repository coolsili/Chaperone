package com.lww.lwwlibrary.retrofit;

import android.util.Log;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * https://github.com/bigeyechou/Rxjava2Retrofit2NetFrame
 * 日期 2020/04/13 10:07
 * lww
 */
public class RetrofitHandler {

    protected OkHttpClient mOkHttpClient;
    private static RetrofitHandler mRetrofitHandler;
    private Retrofit mRetrofit;
    private String APP_SERVER_BASE_URL = "https://iomstest.logifasffmis.com/";

    public RetrofitHandler() {
        initRetrofit();
    }

    public static synchronized  RetrofitHandler getInstance() {
        if (mRetrofitHandler == null) {
            synchronized (RetrofitHandler.class) {
                if (mRetrofitHandler == null) {
                    mRetrofitHandler = new RetrofitHandler();
                }
            }
        }
        return mRetrofitHandler;
    }

    public void setBaseUrl(String host){
        this.APP_SERVER_BASE_URL = host;
        initRetrofit();
    }

    /**
     * 获取 Retrofit
     */
    protected void initRetrofit() {
        Log.e("TAG", "RetrofitHandler.java");
        initOkHttpClient();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(APP_SERVER_BASE_URL)
                //Json转换器，用Gson来转换
                .addConverterFactory(GsonConverterFactory.create())
                //Rxjava适配器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(mOkHttpClient)
                .build();
//        t = mRetrofit.create();
    }

    /**
     * 单例模式获取 OkHttpClient
     */
    private void initOkHttpClient(){
        if (mOkHttpClient == null) {
            synchronized (RetrofitHandler.class) {
                if (mOkHttpClient == null) {
                    // 指定缓存路径,缓存大小100Mb
//                    Cache cache = new Cache(new File(HttpConfig.DIR_CACHE_FILE, "HttpCache"),
//                            1024 * 1024 * 100);
                    TrustManager[] trustAllCerts = new TrustManager[] {
                            new X509TrustManager() {
                                public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[0]; }
                                public void checkClientTrusted(X509Certificate[] certs, String authType) {}
                                public void checkServerTrusted(X509Certificate[] certs, String authType) {}
                            }
                    };


                    SSLContext sc = null;
                    try {
                        sc = SSLContext.getInstance("TLS");
                    } catch (NoSuchAlgorithmException e) {
                        throw new RuntimeException(e);
                    }
                    try {
                        sc.init(null, trustAllCerts, new SecureRandom());
                    } catch (KeyManagementException e) {
                        throw new RuntimeException(e);
                    }
                    mOkHttpClient = new OkHttpClient.Builder()
                            //设置连接超时时间
                            .connectTimeout(HttpConfig.HTTP_TIME_OUT_TIME, TimeUnit.SECONDS)
                            //设置读取超时时间
                            .readTimeout(HttpConfig.HTTP_TIME_OUT_TIME, TimeUnit.SECONDS)
                            //设置写入超时时间
                            .writeTimeout(HttpConfig.HTTP_TIME_OUT_TIME, TimeUnit.SECONDS)
                            //默认重试一次
                            .retryOnConnectionFailure(true)
                            //添加请求头拦截器
                            .addInterceptor(InterceptorHelper.getHeaderInterceptor())
                            //添加日志拦截器
                            .addInterceptor(InterceptorHelper.getLogInterceptor())
                            //添加缓存拦截器
//                            .addInterceptor(InterceptorHelper.getCacheInterceptor())
                            //添加重试拦截器
                            .addInterceptor(InterceptorHelper.getRetryInterceptor())
                            // 信任Https,忽略Https证书验证
                            // https认证,如果要使用https且为自定义证书 可以去掉这两行注释，并自行配制证书。
                            .sslSocketFactory(sc.getSocketFactory(),(X509TrustManager)trustAllCerts[0])
                            .hostnameVerifier(((hostname, session) -> true))
                            //缓存
//                            .cache(cache)
                            .build();
                }
            }
        }
    }

    public OkHttpClient getmOkHttpClient(){
        return mOkHttpClient;
    }

    /**
     * 对外提供调用 API的接口
     *
     * @return
     */
    public <T> T getAPIService(Class<T> service) {
        return mRetrofit.create(service);
    }
}
