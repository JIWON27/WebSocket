package com.example.websocket.controller;

import com.example.websocket.domain.ChatRoom;
import com.example.websocket.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {
  // 채팅방 생성 및 조회

  private final ChatService chatService;

  // 채팅방 생성
  @PostMapping
  public ChatRoom createRoom(@RequestParam String name) {
    return chatService.createRoom(name);
  }

  // 전체 조회
  @GetMapping
  public List<ChatRoom> findAllRoom() {
    return chatService.findAllRoom();
  }
  // 단건 조회
  @GetMapping("/{roomId}")
  public ChatRoom findAllRoom(@PathVariable String roomId) {
    return chatService.findRoomById(roomId);
  }
}
