package com.example.websocket.controller;

import com.example.websocket.domain.ChatRoom;
import com.example.websocket.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@Controller
@RequestMapping("/chat-api")
public class ChatRoomController {

  private final ChatRoomRepository chatRoomRepository;

  // 채팅 리스트 화면
  @GetMapping("/room")
  public String rooms(Model model) {
    model.addAttribute("chatRooms", chatRoomRepository.findAllRoom());
    return "chat/room";
  }
  // 모든 채팅방 목록 반환
  @GetMapping("/rooms")
  @ResponseBody
  public List<ChatRoom> room() {
    return chatRoomRepository.findAllRoom();
  }
  // 채팅방 생성
  @PostMapping("/room")
  @ResponseBody
  public ChatRoom createRoom(@RequestParam String name) {
    return chatRoomRepository.createChatRoom(name);
  }
  // 채팅방 입장 화면
  @GetMapping("/room/enter/{roomId}")
  public String roomDetail(Model model, @PathVariable String roomId) {
    ChatRoom room = chatRoomRepository.findRoomById(roomId);
    model.addAttribute("room", room);
    return "chat/room_detail";
  }
  // 특정 채팅방 조회
  @GetMapping("/room/{roomId}")
  @ResponseBody
  public ChatRoom roomInfo(Model model,@PathVariable String roomId) {
    return chatRoomRepository.findRoomById(roomId);
  }
}
