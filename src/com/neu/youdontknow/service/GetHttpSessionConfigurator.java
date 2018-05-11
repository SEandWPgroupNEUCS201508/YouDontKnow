package com.neu.youdontknow.service;

import javax.servlet.http.HttpSession;
        import javax.websocket.HandshakeResponse;
        import javax.websocket.server.HandshakeRequest;
        import javax.websocket.server.ServerEndpointConfig;


public class  GetHttpSessionConfigurator extends ServerEndpointConfig.Configurator {
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        sec.getUserProperties().put("httpSession", (HttpSession) request.getHttpSession());
    }
}
