package com.example.websocket.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
public class ChatRoom {

  private Long id;

  private String roomName;
  private String roomId;
  private Set<WebSocketSession> sessions = new HashSet<>();

  public static ChatRoom create(String name) {
    ChatRoom chatRoom = new ChatRoom();
    chatRoom.roomId = UUID.randomUUID().toString();
    chatRoom.roomName = name;
    return chatRoom;
  }
}

