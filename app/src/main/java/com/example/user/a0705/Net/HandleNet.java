package com.example.user.a0705.Net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * Created by user on 2016/7/19.
 */
public class HandleNet {
    public static final String ConnectionStr = "";



    public interface HttpCallbackListener {

        void onFinish(String response);

        void onError(Exception e);
    }

    public static void HndleNet(final String url,final String content,final HttpCallbackListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;

                try {
                    URL httpUrl = new URL(url);
                    connection = (HttpURLConnection) httpUrl.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setReadTimeout(8000);
                    connection.setConnectTimeout(8000);

                    OutputStream out = connection.getOutputStream();
                    out.write(content.getBytes());
                    out.flush();
                    out.close();

                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }

                    reader.close();

                    if (listener != null) {
                        listener.onFinish(response.toString());
                    }

                } catch (MalformedURLException e) {
                    if (listener != null) {
                        listener.onError(e);
                    }

                } catch (IOException e) {
                    if (listener != null) {
                        listener.onError(e);
                    }

                } finally {
                    if (connection != null)
                        connection.disconnect();
                }
            }
        }).start();
    }}