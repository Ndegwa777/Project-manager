package com.wera.wera.service;

import java.util.List;

import com.wera.wera.entities.Message;

public interface MessageService {
    
    Message sendMessage(Long senderId, Long projectId, String content) throws Exception;

    List<Message> getMessagesByProjectId(Long projectId) throws Exception;
}
