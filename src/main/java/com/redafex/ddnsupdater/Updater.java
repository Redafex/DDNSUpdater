package com.redafex.ddnsupdater;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Updater {

    static CloseableHttpClient client = HttpClients.custom().build();
    static HttpPost post;

    static boolean failed = false;
    static String ip = "";

    static SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd HH:mm:ss");
    static long epoch = 0;

    static long nextExc = System.currentTimeMillis();

    public static void check() {
        while (true) {
            try {
                epoch = System.currentTimeMillis();
                if (epoch < nextExc) continue;
                nextExc = epoch + Main.config.options.updateTime;

//                Thread.sleep(Main.config.options.updateTime);


                if(getIP().equals(ip) && !failed){
                    continue;
                }
                System.out.print("Updated ip " + ip);
                ip = getIP();
                System.out.println(" ----------> " + ip + " on " + dateFormat.format(new Date(epoch)));
                UpdateDdns();
                failed = false;
            } catch (Exception e) {
                failed = true;
                System.out.println("##### Currently offline #####");
            }
        }
    }


    public static String getIP() throws Exception {
        return EntityUtils.toString(client.execute(new HttpGet("https://api.ipify.org")).getEntity());
    }

    public static void UpdateDdns() throws Exception{
        post.setEntity(new StringEntity("{ \"name\": \"redafex.freeddns.com\",\"ipv4Address\": \""+ ip +"\",\"ttl\": 90,}", ContentType.APPLICATION_JSON));
    }
}
