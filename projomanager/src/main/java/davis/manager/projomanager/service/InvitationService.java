package com.wera.wera.service;

import com.wera.wera.entities.Invitation;

import jakarta.mail.MessagingException;

public interface InvitationService {
    
    public void sendInvitation(String email, Long projectId) throws MessagingException;

    public Invitation accepInvitation(String token, Long userId) throws Exception;

    public String getTokenByUserMail(String userEmail) throws Exception;

    void deleteToken(String token) throws Exception;
}
