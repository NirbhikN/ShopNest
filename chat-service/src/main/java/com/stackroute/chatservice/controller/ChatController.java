package com.stackroute.chatservice.controller;

import com.stackroute.chatservice.exception.ChatAlreadyExistException;
import com.stackroute.chatservice.exception.ChatNotFoundException;
import com.stackroute.chatservice.exception.NoChatExistsInTheRepository;
import com.stackroute.chatservice.model.Chat;
import com.stackroute.chatservice.model.Message;
import com.stackroute.chatservice.repository.ChatRepository;
import com.stackroute.chatservice.service.ChatService;
import com.stackroute.chatservice.service.SequenceGeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/chats")
@Slf4j
public class ChatController {


    private ChatRepository chatRepository;
    private ChatService chatService;
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    public ChatController(ChatRepository chatRepository, ChatService chatService, SequenceGeneratorService sequenceGeneratorService) {
        this.chatRepository = chatRepository;
        this.chatService = chatService;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @PostMapping("/add")
    public ResponseEntity<Chat> createChat(@RequestBody Chat chat) throws IOException {

        try {
            log.debug("Inside the ChatController -- createchat methods");
            return new ResponseEntity<Chat>(chatService.addChat(chat), HttpStatus.CREATED);
        } catch (ChatAlreadyExistException e) {
            log.error("Chat already exists", e);
            return new ResponseEntity("Chat Already Exist", HttpStatus.CONFLICT);
        }
    }

//    @Autowired
    @GetMapping("/all")
    public ResponseEntity<List<Chat>> getAllChats() {
        try {
            log.debug("Inside the ChatController -- getAllChats methods");
            return new ResponseEntity<List<Chat>>(chatService.findallchats(), HttpStatus.OK);
        } catch (NoChatExistsInTheRepository e) {
            log.error("Chat not found", e);
            return new ResponseEntity("List not found", HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Chat> getChatById(@PathVariable String id) {
        try {
            log.debug("Inside the ChatController -- getChatById methods");
            return new ResponseEntity<Chat>(chatService.getById(id), HttpStatus.OK);
        } catch (ChatNotFoundException e) {
            log.error("Chat not found.", e);
            return new ResponseEntity("Chat Not Found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/buyerEmail/{email}")
    public ResponseEntity<?> getChatByBuyerEmail(@PathVariable String email) {
        try {
            HashSet<Chat> byChat = this.chatService.getChatByBuyerId(email);
            return new ResponseEntity<>(byChat, HttpStatus.OK);
        } catch (ChatNotFoundException e) {
            return new ResponseEntity("Chat Not Exits", HttpStatus.CONFLICT);
        }
    }



    @GetMapping("/ownerEmail/{email}")
    public ResponseEntity<?> getChatByOwnerEmail(@PathVariable String email) {

        try {
            HashSet<Chat> byChat = this.chatService.getChatBySellerId(email);
            return new ResponseEntity<>(byChat, HttpStatus.OK);
        } catch (ChatNotFoundException e) {
            return new ResponseEntity("Chat Not Exits", HttpStatus.CONFLICT);
        }
    }

//    @GetMapping("/chatByOwnerOrBuyerEmail/{email}")
//    public ResponseEntity<?> getChatByOwnerOrEmailBuyerEmail(@PathVariable String email) {
//
//        try {
//            HashSet<Chat> byChat = this.chatService.getChatByOrOwnerEmailBuyerEmail(email);
//            return new ResponseEntity<>(byChat, HttpStatus.OK);
//        } catch (ChatNotFoundException e) {
//            return new ResponseEntity("Chat Not Exits", HttpStatus.CONFLICT);
//        }
//    }


    @GetMapping("/chatByBothOwnerAndBuyerEmail")
    public ResponseEntity<?> getChatByBuyerEmailAndOwnerEmail(@RequestParam("ownerEmail") String ownerEmail, @RequestParam("buyerEmail") String buyerEmail){

        try {
            HashSet<Chat> chatByBothEmail = this.chatService.getChatByOwnerEmailAndBuyerEmail(ownerEmail, buyerEmail);
            return new ResponseEntity<>(chatByBothEmail, HttpStatus.OK);
        } catch (ChatNotFoundException e) {
            return new ResponseEntity("Chat Not Exits", HttpStatus.CONFLICT);
        }
    }


//    @GetMapping("/byBoth")
//    public ResponseEntity<Chat> getChatByBuyerEmailAndOwnerEmail(@RequestParam("ownerEmail") String ownerEmail, @RequestParam("buyerEmail") String buyerEmail){
//
//        try {
//            return new ResponseEntity<Chat>(chatService.getChatByOwnerEmailAndBuyerEmail(ownerEmail, buyerEmail), HttpStatus.OK);
//        } catch (ChatNotFoundException e) {
//            return new ResponseEntity("Chat Not Exits", HttpStatus.CONFLICT);
//        }
//    }

    @PutMapping("/message/{chatId}")
    public ResponseEntity<Chat> addMessage(@RequestBody Message add , @PathVariable String chatId) {

        return new ResponseEntity<Chat>(chatService.addMessage(add,chatId), HttpStatus.OK);
    }


}


