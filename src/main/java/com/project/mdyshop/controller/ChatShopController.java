package com.project.mdyshop.controller;

import com.project.mdyshop.dto.request.CreateChatRequest;
import com.project.mdyshop.dto.request.MessageRequest;
import com.project.mdyshop.exception.ChatException;
import com.project.mdyshop.exception.ShopException;
import com.project.mdyshop.exception.UserException;
import com.project.mdyshop.model.Chat;
import com.project.mdyshop.model.Message;
import com.project.mdyshop.model.Shop;
import com.project.mdyshop.model.User;
import com.project.mdyshop.repository.ShopRepository;
import com.project.mdyshop.service.ChatService;
import com.project.mdyshop.service.MessageService;
import com.project.mdyshop.service.ShopService;
import com.project.mdyshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shop/chat")
@PreAuthorize("hasRole('SHOP')")
public class ChatShopController {

    @Autowired
    ChatService chatService;
    @Autowired
    UserService userService;
    @Autowired
    ShopRepository shopRepository;
    @Autowired
    MessageService messageService;

    @PostMapping("/create")
    public ResponseEntity<Chat> createShopChat(@RequestBody CreateChatRequest request, @RequestHeader("Authorization")String jwt) throws UserException, ShopException {
        User user = userService.findUserByToken(jwt);
        Shop shop = shopRepository.findShopByUser(user.getId());

        Chat chat = chatService.createChat(request.getUser(), shop);

        return new ResponseEntity<>(chat, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Chat>> getUserChats(@RequestHeader("Authorization")String jwt) throws UserException {
        User user = userService.findUserByToken(jwt);

        List<Chat> chats = chatService.findUserChats(user.getId());

        return new ResponseEntity<>(chats, HttpStatus.ACCEPTED);
    }

    @PostMapping("/message")
    public ResponseEntity<Message> createMessage(@RequestHeader("Authorization")String jwt,
                                                 @RequestBody MessageRequest request) throws UserException, ChatException {
        User user = userService.findUserByToken(jwt);
        Shop shop = shopRepository.findShopByUser(user.getId());

        Message message = messageService.createShopMessage(shop, request);

        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
}
