package com.example.websocket.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

import java.util.*;

@Getter
public class ChatRoom {

  private Long id;

  private String username; // 채팅방 생성자
  private List<User> users = new ArrayList<>(); // 채팅방에 속한 사람들

  private String roomName;
  private String roomId;
  private Set<WebSocketSession> sessions = new HashSet<>();

  public static ChatRoom create(String name) {
    ChatRoom chatRoom = new ChatRoom();
    chatRoom.roomId = UUID.randomUUID().toString();
    chatRoom.roomName = name;
    return chatRoom;
  }

  // 채팅방 입장
  public void addUser(User user){
    if(!this.users.contains(user)) {
      this.users.add(user);
    }
  }
}

