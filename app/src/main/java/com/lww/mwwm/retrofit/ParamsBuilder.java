package com.lww.mwwm.retrofit;

import java.util.HashMap;

public class ParamsBuilder {

    public static ParamsBuilder getIntance() {
        return new ParamsBuilder();
    }

    private HashMap<String, Object> params = new HashMap<>();

    public ParamsBuilder addParams(String key, Object value) {
        params.put(key, value);
        return this;
    }

    public HashMap<String, Object> get() {
        return params;
    }
}
