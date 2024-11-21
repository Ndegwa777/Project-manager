package com.wera.wera.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wera.wera.entities.Message;

public interface MessageRepository extends JpaRepository<Message, Long>{
    
    List<Message>findByChatIdOrderByCreatedAtAsc(Long chatId);
}
