package com.stackroute.chatservice.repository;

import com.stackroute.chatservice.model.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.HashSet;

public interface ChatRepository extends MongoRepository<Chat, String> {
    HashSet<Chat> getChatByBuyerId(String id);

    HashSet<Chat> getChatBySellerId(String id);

    HashSet<Chat> getChatBySellerAndBuyerId(String sellerId, String buyerId);

    HashSet<Chat> getChatByBuyerAndSellerId(String sellerId, String buyerId);

//    HashSet<Chat> getChatByOrOwnerEmailOrBuyerEmail(String email);

//    Optional<Chat> findById(String ownerEmail, String buyerEmail);

}
