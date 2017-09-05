package com.tools.okHttp;


import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.tools.imageCompressor.CompressHelper;
import com.tools.util.AppUtils;
import com.tools.util.PhoneUtils;
import com.tools.utils.MD5Utils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.request.RequestCall;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Callback;
import okhttp3.CookieJar;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;


/**
 *
 */
public class HttpUtils {

    private static String token;

    /**
     * get方式
     *
     * @param url
     * @param httpCallBack
     */
    public static void getWithNullParams(String url, HttpCallback httpCallBack) {
        OkHttpUtils
                .get()
                .url(url)
                .addHeader("version", AppUtils.getAppVersionName())
                .addHeader("imei", PhoneUtils.getIMEI())
                .addHeader("time", String.valueOf(System.currentTimeMillis()))
                .addHeader("token", getToken())
                .build()
                .execute(httpCallBack);
    }

    /**
     * get方式提交键值对数据
     *
     * @param url
     * @param hashMap
     * @param httpCallBack
     */
    public static void getWithParams(String url, HashMap<String, String> hashMap, HttpCallback httpCallBack) {
        OkHttpUtils
                .get()
                .url(url)
                .params(hashMap)
                .addHeader("version", AppUtils.getAppVersionName())
                .addHeader("imei", PhoneUtils.getIMEI())
                .addHeader("time", String.valueOf(System.currentTimeMillis()))
                .addHeader("token", getToken())
                .build()
                .execute(httpCallBack);
    }


    /**
     * get方式提交键值对数据
     *
     * @param url
     * @param hashMap
     * @param id
     * @param httpCallBack
     */
    public static void getWithParams(String url, HashMap<String, String> hashMap, int id, HttpCallback httpCallBack) {
        OkHttpUtils
                .get()
                .url(url)
                .id(id)
                .params(hashMap)
                .addHeader("version", AppUtils.getAppVersionName())
                .addHeader("imei", PhoneUtils.getIMEI())
                .addHeader("time", String.valueOf(System.currentTimeMillis()))
                .addHeader("token", getToken())
                .build()
                .execute(httpCallBack);
    }

    /**
     * get方式获取图片
     *
     * @param url
     * @param tag
     * @param bitmapCallback
     */
    public static void getImage(String url, String tag, BitmapCallback bitmapCallback) {
        OkHttpUtils
                .get()
                .url(url)
                .tag(tag)
                .addHeader("version", AppUtils.getAppVersionName())
                .addHeader("imei", PhoneUtils.getIMEI())
                .addHeader("time", String.valueOf(System.currentTimeMillis()))
                .addHeader("token", getToken())
                .build()
                .connTimeOut(20000)
                .readTimeOut(20000)
                .writeTimeOut(20000)
                .execute(bitmapCallback);
    }

    /**
     * get方式下载文件
     *
     * @param url
     * @param fileCallBack
     */
    public static void downloadFile(String url, FileCallBack fileCallBack) {
        OkHttpUtils
                .get()
                .url(url)
                .addHeader("", "")
                .build()
                .execute(fileCallBack);
    }


    /**
     * post方式提交键值对数据
     *
     * @param url
     * @param httpCallBack
     */
    public static void postWithNullParams(String url, HttpCallback httpCallBack) {
        RequestCall requestCall = OkHttpUtils
                .post()
                .url(url)
                .addHeader("version", AppUtils.getAppVersionName())
                .addHeader("imei", PhoneUtils.getIMEI())
                .addHeader("time", String.valueOf(System.currentTimeMillis()))
                .addHeader("token", getToken())
                .build();
        requestCall.execute(httpCallBack);
    }

    /**
     * post方式提交键值对数据
     *
     * @param url
     * @param hashMap
     * @param httpCallBack
     */
    public static void postWithParams(String url, HashMap<String, String> hashMap, HttpCallback httpCallBack) {
        RequestCall requestCall = OkHttpUtils
                .post()
                .url(url)
                .params(hashMap)
                .addHeader("version", AppUtils.getAppVersionName())
                .addHeader("imei", PhoneUtils.getIMEI())
                .addHeader("time", String.valueOf(System.currentTimeMillis()))
                .addHeader("token", getToken())
                .build();
        requestCall.execute(httpCallBack);
    }

    /**
     * post方式提交文件
     *
     * @param url
     * @param file
     * @param fileCallBack
     */
    public static void postFile(String url, File file, FileCallBack fileCallBack) {
        OkHttpUtils
                .postFile()
                .url(url)
                .file(file)
                .addHeader("version", AppUtils.getAppVersionName())
                .addHeader("imei", PhoneUtils.getIMEI())
                .addHeader("time", String.valueOf(System.currentTimeMillis()))
                .addHeader("token", getToken())
                .build()
                .execute(fileCallBack);
    }

    /**
     * 图片上传
     *
     * @param url
     * @param file
     * @param maps
     * @param callback
     */
    public static void postFileImage(String url, File file, Map<String, String> maps, Callback callback) {
        OkHttpClient client = new OkHttpClient();

        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (maps == null) {
            builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"file\";filename=\"file.jpg\""), RequestBody.create(MediaType.parse("image/png"), file)).build();
        } else {
            for (String key : maps.keySet()) {
                builder.addFormDataPart(key, maps.get(key));
            }

            builder.addPart(Headers.of("Content-Disposition", "form-data; name=\"file\";filename=\"file.jpg\""), RequestBody.create(MediaType.parse("image/png"), file)
            );

        }
        RequestBody body = builder.build();
        final Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("version", AppUtils.getAppVersionName())
                .addHeader("imei", PhoneUtils.getIMEI())
                .addHeader("time", String.valueOf(System.currentTimeMillis()))
                .addHeader("token", getToken())
                .build();
        client.newCall(request).enqueue(callback);
    }


    /**
     * 图片上传
     *
     * @param url
     * @param files
     * @param callback
     */
    public static void postFileImages(Context context, String url, List<File> files, Callback callback) {
        OkHttpClient client = new OkHttpClient();
        MultipartBody.Builder builder = new MultipartBody
                .Builder()
                .setType(MultipartBody.FORM);


        for (int i = 0; i < files.size(); i++) {
            String namesAndValues = "form-data; name=\"file" + (i + 1) + "\";filename=\"file+" + (i + 1) + ".jpg\"";
            File newFile = CompressHelper.getDefault(context).compressToFile(files.get(i));

//            builder.addPart(Headers.of("Content-Disposition", namesAndValues), RequestBody.create(MediaType.parse("image/png"),
//                            files.get(i)));
            builder.addPart(Headers.of("Content-Disposition", namesAndValues), RequestBody.create(MediaType.parse("image/png"), newFile));
        }

        RequestBody body = builder.build();
        final Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("version", AppUtils.getAppVersionName())
                .addHeader("imei", PhoneUtils.getIMEI())
                .addHeader("time", String.valueOf(System.currentTimeMillis()))
                .addHeader("token", getToken())
                .build();
        client.newCall(request).enqueue(callback);
    }


    /**
     * post方式提交文件
     *
     * @param url
     * @param file
     * @param httpCallBack
     */
    public static void postFile(String url, File file, HttpCallback httpCallBack) {
        OkHttpUtils
                .postFile()
                .file(file)
                .url(url)
                .addHeader("version", AppUtils.getAppVersionName())
                .addHeader("imei", PhoneUtils.getIMEI())
                .addHeader("time", String.valueOf(System.currentTimeMillis()))
                .addHeader("token", getToken())
                .build()
                .execute(httpCallBack);
    }

    /**
     * post方式提交键值对数据，同时提交文件
     *
     * @param url
     * @param hashMap
     * @param file
     * @param fileType     例如：image
     * @param fileName
     * @param httpCallBack
     */
    public static void postParamsAndFile(String url, HashMap<String, String> hashMap, File file, String fileType, String fileName, HttpCallback httpCallBack) {
        //可以提交多个文件
        OkHttpUtils
                .post()
                .addFile(fileType, fileName, file)
                .url(url)
                .params(hashMap)
                .addHeader("version", AppUtils.getAppVersionName())
                .addHeader("imei", PhoneUtils.getIMEI())
                .addHeader("time", String.valueOf(System.currentTimeMillis()))
                .addHeader("token", getToken())
                .addHeader("Content-Type", "image/png")
                .addHeader("enctype", "multipart/form-data")
                .build()
                .execute(httpCallBack);

    }


    /**
     * post方式提交Json数据
     *
     * @param url
     * @param obj
     * @param httpCallBack
     */
    public static void postJson(String url, Object obj, HttpCallback httpCallBack) {
        OkHttpUtils
                .postString()
                .url(url)
                .mediaType(MediaType.parse("application/json; charset=utf-8"))
                .content(new Gson().toJson(obj))
                .addHeader("version", AppUtils.getAppVersionName())
                .addHeader("imei", PhoneUtils.getIMEI())
                .addHeader("time", String.valueOf(System.currentTimeMillis()))
                .addHeader("token", getToken())
                .build()
                .execute(httpCallBack);
    }


    /**
     * 清除Session
     */
    public static void clearSession() {
        CookieJar cookieJar = OkHttpUtils.getInstance().getOkHttpClient().cookieJar();
        if (cookieJar instanceof CookieJarImpl) {
            ((CookieJarImpl) cookieJar).getCookieStore().removeAll();
        }
    }

    //其他请求方式
    public static void otherRequestDemo(HttpCallback httpCallBack) {
        OkHttpUtils
                .put()
                .url("http://11111.com")
                .requestBody("may be something")
                .addHeader("", "")
                .build()
                .execute(httpCallBack);
        try {
            OkHttpUtils
                    .head()
                    .url("http://11111.com")
                    .addParams("name", "zhy")
                    .addHeader("", "")
                    .build()
                    .execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 将Unicode编码解析成字符串形式（如汉字）
     *
     * @param uString
     * @return
     */
    public static String decodeUnicodeToString(String uString) {
        StringBuilder sb = new StringBuilder();
        int i = -1, pos = 0;
        while ((i = uString.indexOf("\\u", pos)) != -1) {
            sb.append(uString.substring(pos, i));
            if (i + 5 < uString.length()) {
                pos = i + 6;
                sb.append((char) Integer.parseInt(uString.substring(i + 2, i + 6), 16));
            }
        }
        sb.append(uString.substring(pos));
        return sb.toString();
    }

    public static String getToken() {
        //ma5(md5(版本)+md5(imei)+'sound20170309@@$*')
        if (TextUtils.isEmpty(token)) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(MD5Utils.getMd5Value(AppUtils.getAppVersionName()));
            stringBuffer.append(MD5Utils.getMd5Value(PhoneUtils.getIMEI()));
            stringBuffer.append("sound20170309@@$*");
            token = MD5Utils.getMd5Value(stringBuffer.toString());
        }
        return token;
    }
}