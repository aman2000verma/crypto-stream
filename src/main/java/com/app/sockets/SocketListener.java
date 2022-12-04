package com.app.sockets;

import java.util.List;
import java.util.Map;

import com.app.utils.LogUtil;
import com.neovisionaries.ws.client.ThreadType;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFrame;
import com.neovisionaries.ws.client.WebSocketListener;
import com.neovisionaries.ws.client.WebSocketState;

public class SocketListener implements WebSocketListener {

    @Override
    public void handleCallbackError(WebSocket arg0, Throwable arg1) throws Exception {
        LogUtil.error("Error:\n" + arg1.getMessage());
    }

    @Override
    public void onBinaryFrame(WebSocket arg0, WebSocketFrame arg1) throws Exception {

    }

    @Override
    public void onBinaryMessage(WebSocket arg0, byte[] arg1) throws Exception {

    }

    @Override
    public void onCloseFrame(WebSocket arg0, WebSocketFrame arg1) throws Exception {

    }

    @Override
    public void onConnectError(WebSocket arg0, WebSocketException arg1) throws Exception {
        LogUtil.error("Error:\n" + arg1.getMessage());
        arg0.recreate().connect();
    }

    @Override
    public void onConnected(WebSocket arg0, Map<String, List<String>> arg1) throws Exception {
        LogUtil.console("Connected...");
    }

    @Override
    public void onContinuationFrame(WebSocket arg0, WebSocketFrame arg1) throws Exception {

    }

    @Override
    public void onDisconnected(WebSocket arg0, WebSocketFrame arg1, WebSocketFrame arg2, boolean arg3)
            throws Exception {
        LogUtil.console("Disconnected");
    }

    @Override
    public void onError(WebSocket arg0, WebSocketException arg1) throws Exception {
        LogUtil.error("Error:\n" + arg1.getMessage());
    }

    @Override
    public void onFrame(WebSocket arg0, WebSocketFrame arg1) throws Exception {

    }

    @Override
    public void onFrameError(WebSocket arg0, WebSocketException arg1, WebSocketFrame arg2) throws Exception {
        LogUtil.error("Error:\n" + arg1.getMessage());
    }

    @Override
    public void onFrameSent(WebSocket arg0, WebSocketFrame arg1) throws Exception {

    }

    @Override
    public void onFrameUnsent(WebSocket arg0, WebSocketFrame arg1) throws Exception {

    }

    @Override
    public void onMessageDecompressionError(WebSocket arg0, WebSocketException arg1, byte[] arg2) throws Exception {
        LogUtil.error("Error:\n" + arg1.getMessage());
    }

    @Override
    public void onMessageError(WebSocket arg0, WebSocketException arg1, List<WebSocketFrame> arg2) throws Exception {
        LogUtil.error("Error:\n" + arg1.getMessage());
    }

    @Override
    public void onPingFrame(WebSocket arg0, WebSocketFrame arg1) throws Exception {

    }

    @Override
    public void onPongFrame(WebSocket arg0, WebSocketFrame arg1) throws Exception {

    }

    @Override
    public void onSendError(WebSocket arg0, WebSocketException arg1, WebSocketFrame arg2) throws Exception {
        LogUtil.error("Error:\n" + arg1.getMessage());
    }

    @Override
    public void onSendingFrame(WebSocket arg0, WebSocketFrame arg1) throws Exception {

    }

    @Override
    public void onSendingHandshake(WebSocket arg0, String arg1, List<String[]> arg2) throws Exception {

    }

    @Override
    public void onStateChanged(WebSocket arg0, WebSocketState arg1) throws Exception {

    }

    @Override
    public void onTextFrame(WebSocket arg0, WebSocketFrame arg1) throws Exception {

    }

    @Override
    public void onTextMessage(WebSocket arg0, String arg1) throws Exception {
        LogUtil.console("Received:\n" + arg1);
    }

    @Override
    public void onTextMessage(WebSocket arg0, byte[] arg1) throws Exception {

    }

    @Override
    public void onTextMessageError(WebSocket arg0, WebSocketException arg1, byte[] arg2) throws Exception {
        LogUtil.error("Error:\n" + arg1.getMessage());
    }

    @Override
    public void onThreadCreated(WebSocket arg0, ThreadType arg1, Thread arg2) throws Exception {

    }

    @Override
    public void onThreadStarted(WebSocket arg0, ThreadType arg1, Thread arg2) throws Exception {

    }

    @Override
    public void onThreadStopping(WebSocket arg0, ThreadType arg1, Thread arg2) throws Exception {

    }

    @Override
    public void onUnexpectedError(WebSocket arg0, WebSocketException arg1) throws Exception {
        LogUtil.error("Error:\n" + arg1.getMessage());
    }

}
