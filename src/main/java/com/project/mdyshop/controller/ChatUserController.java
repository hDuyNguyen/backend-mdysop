package com.project.mdyshop.controller;

import com.project.mdyshop.config.JwtTokenProvider;
import com.project.mdyshop.dto.request.CreateChatRequest;
import com.project.mdyshop.exception.UserException;
import com.project.mdyshop.model.Chat;
import com.project.mdyshop.model.User;
import com.project.mdyshop.service.ChatService;
import com.project.mdyshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/chat")
public class ChatUserController {

    @Autowired
    ChatService chatService;
    @Autowired
    UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Chat> createUserChat(@RequestBody CreateChatRequest request) {
        Chat createChat = chatService.createChat(request);
        return new ResponseEntity<>(createChat, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Chat>> getUserChats(@RequestHeader("Authorization")String jwt) throws UserException {
        User user = userService.findUserByToken(jwt);

        List<Chat> chats = chatService.findUserChats(user.getId());

        return new ResponseEntity<>(chats, HttpStatus.ACCEPTED);
    }
}
