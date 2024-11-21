package com.wera.wera.service;

import com.wera.wera.entities.PlanType;
import com.wera.wera.entities.Subscription;
import com.wera.wera.entities.User;

public interface SubscriptionService {
    
    Subscription createSubscription(User user) throws Exception;

    Subscription getUsersSubscription(Long userId) throws Exception;

    Subscription upgradeSubscription(Long userId, PlanType planType);

    boolean isValid(Subscription subscription);
}
