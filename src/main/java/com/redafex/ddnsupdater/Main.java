package com.redafex.ddnsupdater;


import org.apache.http.client.methods.HttpPost;

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