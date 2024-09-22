package com.redafex.ddnsupdater;

import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {

    //TODO: make product available for consumers and scalable n shite

    static Config config;

    public static void main(String[] args) throws Exception {

        config = new Config();

        Updater.post = new HttpPost( "https://api.dynu.com/v2/dns/" + config.options.id);
        Updater.post.addHeader("accept", "application/json");
        Updater.post.addHeader("API-Key", config.options.apiKey);

        Updater.check();
    }





}