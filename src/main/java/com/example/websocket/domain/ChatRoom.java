package com.example.websocket.domain;

import com.example.websocket.service.ChatService;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Getter
public class ChatRoom {

  private Long id;

  private String RoomName;
  private String RoomId;
  private Set<WebSocketSession> sessions = new HashSet<>();

  @Builder
  public ChatRoom(String roomName, String roomId) {
    RoomName = roomName;
    RoomId = roomId;
  }
  public void handleActions(WebSocketSession session, ChatMsg chatMessage, ChatService chatService) {
    sessions.add(session);
    chatMessage.setMessage(chatMessage.getSender() + "님이 입장했습니다.");
    sendMessage(chatMessage, chatService);
  }

  public <T> void sendMessage(T message, ChatService chatService) {
    sessions.parallelStream().forEach(session -> chatService.sendMessage(session, message));
  }
}
