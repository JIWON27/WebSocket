package com.example.websocket.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket // STOMP 활성화
@RequiredArgsConstructor
// WebSockChatHandler를 이용하여 WebSocket을 활성화 하기 위한 Config 파일
public class WebSocketConfig implements WebSocketConfigurer {
  private final WebSocketHandler webSocketHandler;

  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    registry.addHandler(webSocketHandler, "/ws/chat").setAllowedOrigins("*");
    // /chat은 WebSocket으로 접속하기위한 End-Point
    // setAllowedOrigins() : CORS 문제 해결 -> 서로 다른 도메인에서 접속 가능
  }

}
