package com.wera.wera.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.wera.wera.entities.Invitation;
import com.wera.wera.repositories.InvitationRepository;

import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class InvitationServiceImpl implements InvitationService{

    private InvitationRepository invitationRepository;

  
    private EmailService emailService;

    @Override
    public void sendInvitation(String email, Long projectId) throws MessagingException {

        String invitationToken = UUID.randomUUID().toString();

        Invitation invitation = new Invitation();
        invitation.setEmail(email);
        invitation.setProjectId(projectId);
        invitation.setToken(invitationToken);

        invitationRepository.save(invitation);

        // String invitationLink = "http://wera-manager.s3-website-us-east-1.amazonaws.com/accept_invitation?token="+invitationToken;
        // emailService.sendEmailWithToken(email, invitationLink);
        String invitationLink = "http://localhost:5173/accept_invitation?token="+invitationToken;
        emailService.sendEmailWithToken(email, invitationLink);
        
    }

    @Override
    public Invitation accepInvitation(String token, Long userId) throws Exception {
        Invitation invitation = invitationRepository.findByToken(token);
        if(invitation == null){
            throw new Exception("Invalid invitation token");
        }
       return invitation;
    }

    @Override
    public String getTokenByUserMail(String userEmail) throws Exception {

        Invitation invitation = invitationRepository.findByEmail(userEmail);

        return invitation.getToken();
    }

    @Override
    public void deleteToken(String token) throws Exception {
        Invitation invitation = invitationRepository.findByToken(token);

        invitationRepository.delete(invitation);
    }
    
}
