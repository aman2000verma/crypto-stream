package com.app.sockets;

import java.io.IOException;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;

public class SocketProvider {
    final int TIMEOUT = 180000;
    final int INTERVAL = 90000;

    private WebSocket ws;

    public SocketProvider(String url) throws Exception {
        ws = connect(url);
        ws.setPingInterval(INTERVAL);
    }

    private WebSocket connect(String url) throws WebSocketException, IOException {
        return new WebSocketFactory().setConnectionTimeout(TIMEOUT).createSocket(url)
                .addListener(new SocketListener())
                .connect();
    }

    public void sendMsg(String msg) {
        ws.sendText(msg);
    }
}
