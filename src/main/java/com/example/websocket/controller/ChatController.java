package com.example.websocket.controller;

import com.example.websocket.domain.ChatMsg;
import com.example.websocket.domain.ChatRoom;
import com.example.websocket.repository.ChatRepository;
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
  private final ChatRepository chatRepository;

  @MessageMapping("/chat/message") // pub는 config에서 붙임.
  public void message(ChatMsg message) {
    if (ChatMsg.MessageType.ENTER.equals(message.getMessageType())) {
      message.setMessage(message.getSender() + "님이 입장하셨습니다.");
      // 입장했을 때 ChatRoom에 Sender 추가하기.
      // 전달받은 sender(중복X 닉네임)로 User를 찾고 추가해줘야함. -> DTO는 제외하겠음.
    } else {
      // 메세지 DB에 저장 -> 메세지 서버에 보낼때 roomId랑 sender, message, createdAt 필수
      chatRepository.save(message);
    }
    // 구독자들에게 메세지 뿌리기
    messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
  }
}
