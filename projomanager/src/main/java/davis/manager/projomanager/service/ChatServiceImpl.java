package com.wera.wera.service;

import org.springframework.stereotype.Service;

import com.wera.wera.entities.Chat;
import com.wera.wera.repositories.ChatRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ChatServiceImpl implements ChatService {

    private ChatRepository chatRepository;

    @Override
    public Chat createChat(Chat chat) {
        return chatRepository.save(chat);
    }
    
}
