package com.lww.lwwlibrary.retrofit;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 调度类
 * lww （2021-04-29 问题遗留）
 */
public class RxTransformerHelper {
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(20, 50,
            60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

    public static <T> ObservableTransformer<T, T> observableIO2Main() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.from(executor)).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> ObservableTransformer<T, T> observableIO2Thread() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.from(executor)).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}


