package com.wera.wera.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wera.wera.entities.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription,Long> {
    
    Subscription findByUserId(Long userId);
}
