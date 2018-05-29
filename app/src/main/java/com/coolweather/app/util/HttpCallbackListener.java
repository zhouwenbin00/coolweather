package com.coolweather.app.util;

/**
 * Created by 28250 on 2018/5/29.
 */

public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
