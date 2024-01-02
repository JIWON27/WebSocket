package com.example.websocket.repository;

import com.example.websocket.domain.ChatMsg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<ChatMsg, Long> {
  // 기본적은 CRUD
}
