package com.project.mdyshop.controller;

import com.project.mdyshop.exception.ChatException;
import com.project.mdyshop.exception.UserException;
import com.project.mdyshop.model.Message;
import com.project.mdyshop.model.User;
import com.project.mdyshop.service.MessageService;
import com.project.mdyshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat/message")
public class MessageController {

    @Autowired
    UserService userService;
    @Autowired
    MessageService messageService;

//    @PostMapping("/{chatId}")
//    public ResponseEntity<Message> createUserMessage(@RequestHeader("Authorization")String jwt,
//                                                     @RequestBody Message message,
//                                                     @RequestParam Long chatId)
//            throws UserException, ChatException {
//
//        User user = userService.findUserByToken(jwt);
//
//        Message message1 = messageService.createUserMessage(user, chatId, message);
//
//        return new ResponseEntity<>(message1, HttpStatus.CREATED);
//    }

}
