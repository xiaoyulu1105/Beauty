package com.lu.beauty.internet;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by dllo on 16/11/22.
 */
public class OkHttpManager {
    private OkHttpClient client;
    private Handler handler;
    private Gson gson;

    private OkHttpManager() {
        client = new OkHttpClient();
        handler = new Handler(Looper.getMainLooper());
        gson = new Gson();
    }

    private static OkHttpManager ourInstance;

    public static OkHttpManager getInstance() {
        if (ourInstance == null){
            synchronized (ourInstance){
                if (ourInstance == null){
                    ourInstance = new OkHttpManager();
                }
            }
        }
        return ourInstance;
    }

    public <Bean> void post(String url, Class<Bean> clazz, ResponseCallBack<Bean> responseCallBack
                                , HashMap<String, String> body){
        FormBody.Builder formBuilder = new FormBody.Builder();
        for (String s : body.keySet()) {
            formBuilder.add(s, body.get(s));
        }

        FormBody formBody = formBuilder.build();
        Request postRequest = new Request.Builder().url(url).post(formBody).build();

        sendHttpRequest(postRequest, clazz, responseCallBack);
    }

    private <Bean> void sendHttpRequest(Request request, final Class<Bean> clazz, final ResponseCallBack<Bean> responseCallBack) {
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 网络请求失败
                handler.post(new ErrorRunnable<Bean>(responseCallBack, e));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().string();

                Bean bean = gson.fromJson(data, clazz);
                // 尝试解析
                try {// 防止奇葩数据  导致解析的失败

                handler.post(new ResponseRunnable<Bean>(responseCallBack, bean));
                }catch (Exception e) {
                    e.printStackTrace();// 把错误信息直接输出

                    handler.post(new ErrorRunnable<Bean>(responseCallBack, e));
                }
            }
        });
    }

    public <Bean> void get(String url, final Class<Bean> clazz, final ResponseCallBack<Bean> responseCallBack) {
        // 构建Request对象
        Request request = new Request.Builder().url(url).build();

        // 发起网络请求
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 请求失败
                handler.post(new ErrorRunnable<Bean>(responseCallBack, e));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().string();
                try {
                    Bean bean = gson.fromJson(data, clazz);

                    handler.post(new ResponseRunnable<Bean>(responseCallBack, bean));
                } catch (Exception e) {
                    e.printStackTrace();// 直接输出错误信息

                    handler.post(new ErrorRunnable<Bean>(responseCallBack, e));
                }
            }
        });
    }

    abstract class HTTPRunnable<Bean> implements Runnable{
        protected ResponseCallBack<Bean> responseCallBack;

        public HTTPRunnable(ResponseCallBack<Bean> responseCallBack) {
            this.responseCallBack = responseCallBack;
        }
    }

    class ErrorRunnable<Bean> extends HTTPRunnable<Bean>{
        private Exception e;

        public ErrorRunnable(ResponseCallBack<Bean> responseCallBack, Exception e) {
            super(responseCallBack);
            this.e = e;
        }

        @Override
        public void run() {
            responseCallBack.onError(e);
        }
    }

    class ResponseRunnable<Bean> extends HTTPRunnable<Bean>{
        private Bean bean;

        public ResponseRunnable(ResponseCallBack<Bean> responseCallBack, Bean bean) {
            super(responseCallBack);
            this.bean = bean;
        }

        @Override
        public void run() {
            responseCallBack.onResponse(bean);
        }
    }


}
