package com.dson.blog.data.source.remote;


import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpHelper {

    private static HttpHelper sInstance = new HttpHelper();
    private ExecutorService mExecutorService;

    private HttpHelper() {
        mExecutorService = Executors.newCachedThreadPool();
    }

    public static HttpHelper getInstance() {
        return sInstance;
    }

    public void start() {
        mExecutorService.execute(new Runnable() {
            @Override
            public void run() {
                doHttpRequest();
            }
        });
    }

    private void doHttpRequest() {
        try {
            URL url = new URL("http://192.168.1.101:8088/");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream is = urlConnection.getInputStream();
            byte[] bb = new byte[1024];
            is.read(bb);
            is.close();
            urlConnection.disconnect();
            String respone = String.valueOf(bb);
            Log.d("Dson", "resp:" + respone);
        } catch (IOException e) {
            Log.d("Dson", e.toString());
        }
    }
}
