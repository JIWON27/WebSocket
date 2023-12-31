package com.example.websocket;
import com.example.websocket.domain.ChatMsg;
import com.example.websocket.domain.ChatRoom;
import com.example.websocket.service.ChatService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
@RequiredArgsConstructor
public class WebSockChatHandler extends TextWebSocketHandler {

  private final ObjectMapper objectMapper;
  private final ChatService chatService;
  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    // 웹소켓 연결되었는지 확인.
    System.out.println("웹소켓 연결 확인");
  }

  @Override
  public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    // 여러 클라이언트에서 보낸 메세지를 처리할 Handler
    String payload = message.getPayload();
    System.out.println("payload = " + payload);
    // 웹소켓 클라이언트로부터 받은 채팅 메시지를 ChatMsg 객체로 변환
    ChatMsg chatMsg = objectMapper.readValue(payload, ChatMsg.class);
    // roomId로 해당 메세지 전달.
    ChatRoom room = chatService.findRoomById(chatMsg.getRoomId());
    room.handleActions(session, chatMsg, chatService);
  }
}
