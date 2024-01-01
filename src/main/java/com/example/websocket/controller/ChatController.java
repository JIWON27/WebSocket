package com.example.websocket.controller;

import com.example.websocket.domain.ChatMsg;
import com.example.websocket.domain.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {
  private final SimpMessageSendingOperations messagingTemplate;

  @MessageMapping("/chat/message")
  public void message(ChatMsg message) {
    if (ChatMsg.MessageType.ENTER.equals(message.getMessageType()))
      message.setMessage(message.getSender() + "님이 입장하셨습니다.");
    messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
  }
}
