package com.wera.wera.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wera.wera.entities.Invitation;

public interface InvitationRepository extends JpaRepository<Invitation, Long>{
    
    Invitation findByToken(String token);

    Invitation findByEmail(String userEmail);
}
