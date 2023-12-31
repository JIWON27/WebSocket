package com.example.websocket.service;

import com.example.websocket.domain.ChatRoom;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {

  private final ObjectMapper objectMapper;
  private Map<String, ChatRoom> chatRooms;

  @PostConstruct
  private void init() {
    chatRooms = new LinkedHashMap<>();
  }
  //모든 채팅방 찾기
  public List<ChatRoom> findAllRoom() {
    return new ArrayList<>(chatRooms.values());
  }
  // roomId로 채팅방 찾기
  public ChatRoom findRoomById(String roomId) {
    return chatRooms.get(roomId);
  }
  // 방 생성
  public ChatRoom createRoom(String name) {
    String randomId = UUID.randomUUID().toString(); // roomId 생성
    ChatRoom chatRoom = ChatRoom.builder()
        .roomId(randomId)
        .roomName(name)
        .build();
    chatRooms.put(randomId, chatRoom);
    return chatRoom;
  }

  public <T> void sendMessage(WebSocketSession session, T message) {
    try {
      session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
    } catch (IOException e) {
      log.error(e.getMessage(), e);
    }
  }
}
