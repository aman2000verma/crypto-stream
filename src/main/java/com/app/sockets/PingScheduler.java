package com.app.sockets;

import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONObject;

public class PingScheduler {

    public static void schedulePing(SocketProvider socket) {
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                socket.sendMsg(new JSONObject().put("event", "ping").toString());
            }
        }, 0, 300000); // 5min
    }
}
