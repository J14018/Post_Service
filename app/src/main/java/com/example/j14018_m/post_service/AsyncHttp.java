package com.example.j14018_m.post_service;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by J14018_M on 2017/02/13.
 */
public class AsyncHttp extends AsyncTask<String, Integer, Boolean> {
    HttpURLConnection urlConnection = null;
    Boolean flg = false;

    String name;
    String value;

    //コンストラクタ
    public AsyncHttp(String name, double latitude , double longitude){
        this.name = name;
        this.value = latitude + "," + longitude;
    }


    //非同期処理ここから
    @Override
    protected Boolean doInBackground(String... contents) {
        String urlinput = "http://10.250.0.79/upload/post.php";
        try {
            URL url = new URL(urlinput);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);

            //POST用パラメータ
            String postDataSample = "name=" + name + "&text=" + value;
            //POST用パラメータ設定
            OutputStream out = urlConnection.getOutputStream();
            out.write(postDataSample.getBytes());
            out.flush();
            out.close();
            Log.d("test", postDataSample);
            //レスポンスを受け取る
            urlConnection.getInputStream();

            flg = true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flg;
    }
}