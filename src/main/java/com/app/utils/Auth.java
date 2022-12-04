package com.app.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.json.JSONObject;

public class Auth {

    final static String API_KEY = Properties.get("API_KEY");
    final static String SECRET_KEY = Properties.get("SECRET_KEY");

    final static long recvWindow = 30000; // 30s
    static String signature = null;
    static String authKey = null;

    public static void generateAuthKey() {
        long timestamp = new Timestamp(System.currentTimeMillis()).getTime();
        signature = new String(calcHmacSha256(SECRET_KEY,
                "recvWindow=" + recvWindow + "&timestamp=" + timestamp));
        if (signature == null) {
            LogUtil.error("Cannot generate websocket auth token...");
            System.exit(1);
        }
        fetchKey(timestamp);
    }

    public static String generateSignature(long timestamp) {
        return new String(calcHmacSha256(SECRET_KEY,
                "recvWindow=" + recvWindow + "&timestamp=" + timestamp));
    }

    private static void fetchKey(long timestamp) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.wazirx.com/sapi/v1/create_auth_token?recvWindow="
                            + recvWindow + "&timestamp=" + timestamp + "&signature=" + signature))
                    .header("X-Api-Key", API_KEY)
                    .POST(BodyPublishers.ofString(""))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject json = new JSONObject(response.body());
            authKey = json.getString("auth_key");
            LogUtil.console("Retrieved websocket auth key...");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String calcHmacSha256(String key, String value) {
        Mac hmac;
        try {
            hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
            hmac.init(secret_key);
            return new String(Hex.encodeHex(hmac.doFinal(value.getBytes("UTF-8"))));
        } catch (NoSuchAlgorithmException | InvalidKeyException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getAuthKey() {
        return authKey;
    }

    public static String getSignature() {
        long timestamp = new Timestamp(System.currentTimeMillis()).getTime();
        generateSignature(timestamp);
        return signature;
    }
}
