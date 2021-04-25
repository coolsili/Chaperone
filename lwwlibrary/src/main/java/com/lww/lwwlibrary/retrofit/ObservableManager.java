package com.lww.lwwlibrary.retrofit;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 日期 2020/04/13 10:07
 * lww
 */
public class ObservableManager {

    private static class SingletonHolder {
        static final ObservableManager INSTANCE = new ObservableManager();
    }

    public static ObservableManager getInstance() {
        return ObservableManager.SingletonHolder.INSTANCE;
    }

    public RequestBody getRequestBody(HashMap<String, Object> hashMap) {
        StringBuffer data = new StringBuffer();
        if (hashMap != null && hashMap.size() > 0) {
            Iterator iter = hashMap.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                Object key = entry.getKey();
                Object val = entry.getValue();
                data.append(key).append("=").append(val).append("&");
            }
        }
        String jso = data.substring(0, data.length() - 1);
        MediaType mediaType=MediaType.Companion.parse("application/x-www-form-urlencoded; charset=utf-8");
        RequestBody requestBody = RequestBody.Companion.create(jso,mediaType);
        return requestBody;
    }

}

