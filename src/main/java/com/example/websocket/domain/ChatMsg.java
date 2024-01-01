package com.example.websocket.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class ChatMsg {

  public enum MessageType {
    ENTER, TALK
  }

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  private Long id;

  private String roomId;
  private String sender;
  private String message;
  private MessageType messageType;

}
