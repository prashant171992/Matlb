package com.matlb;

import com.google.gson.Gson;
import com.matlb.domain.Notification;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.nio.client.DefaultHttpAsyncClient;
import org.apache.http.nio.client.HttpAsyncClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by prassingh on 9/25/16.
 */
public class NetworkCall {

    static RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(5 * 1000).build();
    //static HttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();


    public static void makeGetRequest(String url) throws IOException {
        HttpAsyncClient httpclient = new DefaultHttpAsyncClient();
        httpclient.start();
        HttpGet request = new HttpGet(url);

        Future<HttpResponse> future = httpclient.execute(request, null);

//        System.out.println("\nSending 'GET' request to URL : " + url);
//        System.out.println("Response Code : " +
//                response.getStatusLine().getStatusCode());
//
//        BufferedReader rd = new BufferedReader(
//                new InputStreamReader(response.getEntity().getContent()));
//
//        StringBuffer result = new StringBuffer();
//        String line = "";
//        while ((line = rd.readLine()) != null) {
//            result.append(line);
//        }
//
//        System.out.println(result.toString());

    }

    public static void makePostRequest(Notification notification, String url, String authorizationKey) throws IOException {

        HttpAsyncClient httpclient = new DefaultHttpAsyncClient();
        httpclient.start();
        HttpPost post = new HttpPost(url);

        // add header
        post.setHeader("Content-Type", "application/json");
        post.setHeader("Authorization", authorizationKey);

//        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
//        urlParameters.add(new BasicNameValuePair("sn", "C02G8416DRJM"));
//        urlParameters.add(new BasicNameValuePair("cn", ""));
//        urlParameters.add(new BasicNameValuePair("locale", ""));
//        urlParameters.add(new BasicNameValuePair("caller", ""));
//        urlParameters.add(new BasicNameValuePair("num", "12345"));

        Gson gson = new Gson();
        String urlParameters = gson.toJson(notification);

        post.setEntity(new StringEntity(urlParameters));

        Future<HttpResponse> future  = httpclient.execute(post, null);
        HttpResponse response = null;
        try {
            response = future.get();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + post.getEntity());
            int responseCode = response.getStatusLine().getStatusCode();
            System.out.println("Response Code : " + String.valueOf(responseCode));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


//        if (responseCode == 200) {
//            return true;
//
////        BufferedReader rd = new BufferedReader(
////                new InputStreamReader(response.getEntity().getContent()));
////
////        StringBuffer result = new StringBuffer();
////        String line = "";
////        while ((line = rd.readLine()) != null) {
////            result.append(line);
////        }
////
////        System.out.println(result.toString());
//
//        }
    }



}
