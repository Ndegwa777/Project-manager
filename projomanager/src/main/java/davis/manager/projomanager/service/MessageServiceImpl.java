package com.wera.wera.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wera.wera.entities.Chat;
import com.wera.wera.entities.Message;
import com.wera.wera.entities.User;
import com.wera.wera.repositories.MessageRepository;


import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MessageServiceImpl implements MessageService {

    private UserService userService;

    private MessageRepository messageRepository;

    private ProjectService projectService;



    @Override
    public Message sendMessage(Long senderId, Long projectId, String content) throws Exception {
       
        User sender = userService.findUserById(senderId);

        if(sender == null){
            throw new Exception("User not found with Id "+ senderId);
        }
        Chat chat = projectService.getProjectById(projectId).getChat();

        Message message = new Message();
        message.setContent(content);
        message.setSender(sender);
        message.setCreatedAt(LocalDateTime.now());
        message.setChat(chat);
        Message savedMessage = messageRepository.save(message);

        return savedMessage;


    }

    @Override
    public List<Message> getMessagesByProjectId(Long projectId) throws Exception {
        Chat chat = projectService.getChatByProjectId(projectId);

        List<Message> findMessagesByOrder = messageRepository.findByChatIdOrderByCreatedAtAsc(chat.getId());
        return findMessagesByOrder;
    }
    
}
