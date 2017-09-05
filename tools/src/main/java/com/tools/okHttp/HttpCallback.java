package com.tools.okHttp;

import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by xu on 2017/3/9.
 */

public abstract class HttpCallback extends StringCallback {

    public abstract void onError(Call call, Exception e, int id);

    public abstract void onResponse(String response, int id);
}