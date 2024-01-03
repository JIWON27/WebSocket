package com.example.websocket.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
public class ChatMsg {

  public enum MessageType {
    ENTER, TALK
  }

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long id;

  private String roomId; //roomId랑 조인해서 해당 room에만 보여지게하면될듯
  private String sender; //sender가 나면 오른쪽에 위치
  private String message;
  private MessageType messageType;

}
