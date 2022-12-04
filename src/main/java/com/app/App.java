package com.app;

import java.io.*;
import java.net.InetSocketAddress;

import org.json.JSONArray;
import org.json.JSONObject;

import com.app.sockets.PingScheduler;
import com.app.sockets.SocketProvider;
import com.app.utils.Auth;
import com.app.utils.LogUtil;
import com.app.utils.Properties;
import com.sun.net.httpserver.*;

public class App {
    final static int PORT = Integer.parseInt(Properties.get("PORT"));
    final static String WS_URL = Properties.get("WS_URL");

    public static void main(String[] args) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(5000), 0);
            server.createContext("/", new Handler());
            server.start();
            LogUtil.console("Server running on port " + PORT);
            LogUtil.console("Establishing socket connection...");
            try {
                SocketProvider socket = new SocketProvider(WS_URL);
                PingScheduler.schedulePing(socket);
                Auth.generateAuthKey();
                String key = Auth.getAuthKey();
                JSONObject msg = new JSONObject();
                msg.put("event", "subscribe");
                msg.put("streams", new JSONArray().put("usdtinr@kline_1m"));
                msg.put("auth_key", key);
                socket.sendMsg(msg.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Handler implements HttpHandler {
    @Override
    public void handle(HttpExchange xchg) throws IOException {
        String uri = xchg.getRequestURI().toString();
        String method = xchg.getRequestMethod().toString();
        LogUtil.console(method + " => " + uri);
        if (method.toUpperCase().equals("GET")) {
            switch (uri) {
                case "/" -> LogUtil.console("Ping successful!");
                case "/connect" -> LogUtil.console("Connecting to socket...");
                case "/stop" -> LogUtil.console("Disconnecting from socket server...");
                default -> LogUtil.warn("Invalid Request");
            }
        } else {
            LogUtil.error(method + " method not allowed");
        }
    }

}
