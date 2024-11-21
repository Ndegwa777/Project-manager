package com.wera.wera.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wera.wera.entities.Chat;
import com.wera.wera.entities.Message;

import com.wera.wera.request.CreateMessageRequest;
import com.wera.wera.service.MessageService;
import com.wera.wera.service.ProjectService;



import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private MessageService messageService;

    private ProjectService projectService;


    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestBody CreateMessageRequest req) throws Exception{

        // User user = userService.findUserById(req.getSenderId());

        Chat chats = projectService.getProjectById(req.getProjectId()).getChat();
        if(chats == null) throw new Exception("Chats not found");

        Message sentMessage = messageService.sendMessage(req.getSenderId(), req.getProjectId(), req.getContent());

        return new ResponseEntity<>(sentMessage, HttpStatus.OK);
    }


    @GetMapping("/chat/{projectId}")
    public ResponseEntity<List<Message>> getMessagesByChatId(@PathVariable Long projectId) throws Exception{

        List<Message> messages = messageService.getMessagesByProjectId(projectId);

        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    
}
