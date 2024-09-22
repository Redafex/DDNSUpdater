package com.redafex.ddnsupdater;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.yaml.snakeyaml.Yaml;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonValue;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Objects;

public class Config {
    private Yaml yml = new Yaml();
    Options options;

    public Config() throws IOException, URISyntaxException {
        File cfg = new File("config.yml");

        System.out.println(cfg.exists() ? "Config file already exists" : "Config file doesn't exists ----- Attempting to create a new one");

        if (!cfg.exists()) {
            System.out.println(yml.getClass().getResource("/defaultConfig.yml"));
            cfg.createNewFile();
            System.out.println(Files.copy(
                    new File(yml.getClass().getResource("/defaultConfig.yml").toURI()).toPath(),
                    cfg.toPath(), StandardCopyOption.REPLACE_EXISTING).toAbsolutePath());
        }

        HashMap<String, Object> loaded = yml.load(Files.newInputStream(cfg.toPath()));
        options = new Options();
        options.apiKey = (String) loaded.get("api_key");
        options.ddnsName = (String) loaded.get("ddns_name");
        // double casting to make it unnecessary for the user to put 'f' as a suffix
        options.updateTime = (float) ((int) loaded.get("update_length"));

        options.id = getId(options.ddnsName);
        System.out.println("### ID found ###");
    }

    public  int getId(String ddnsName) throws IOException {
        HttpGet get = new HttpGet( "https://api.dynu.com/v2/dns");

        get.addHeader("accept", "application/json");
        get.addHeader("API-Key", options.apiKey);

        String res = EntityUtils.toString(Updater.client.execute(get).getEntity());

        JsonArray jsonArr = Json.createReader(new StringReader(res)).readObject().getJsonArray("domains");

        for (JsonValue val : jsonArr){
            if (Objects.equals(val.asJsonObject().getString("name"), ddnsName)){
                System.out.println("### Ddns Found ###");
                return val.asJsonObject().getJsonNumber("id").intValue();
            }
        }
        return 0;
    }

    class Options{
        public String apiKey;
        public String ddnsName;
        public float updateTime;
        public int id;
    }
}
