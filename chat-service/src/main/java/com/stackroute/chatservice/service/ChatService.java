package com.stackroute.chatservice.service;

import com.stackroute.chatservice.exception.ChatAlreadyExistException;
import com.stackroute.chatservice.exception.ChatNotFoundException;
import com.stackroute.chatservice.exception.NoChatExistsInTheRepository;
import com.stackroute.chatservice.model.Chat;
import com.stackroute.chatservice.model.Message;

import java.util.HashSet;
import java.util.List;

public interface ChatService {

    public Chat addChat(Chat chat) throws ChatAlreadyExistException;

    List<Chat> findallchats() throws NoChatExistsInTheRepository;

    Chat getById(String chatId) throws ChatNotFoundException;

    HashSet<Chat> getChatByBuyerId(String email) throws ChatNotFoundException;

    HashSet<Chat> getChatBySellerId(String email) throws ChatNotFoundException;

    HashSet<Chat> getChatByOwnerEmailAndBuyerEmail(String ownerEmail, String buyerEmail) throws ChatNotFoundException;
    Chat addMessage(Message add, String chatId);

//     Chat getChatByOwnerEmailAndBuyerEmail(String ownerEmail, String buyerEmail) throws ChatNotFoundException;


//    HashSet<Chat> getChatByOrOwnerEmailBuyerEmail(String email) throws ChatNotFoundException;
}
