package com.project.mdyshop.service;

import com.project.mdyshop.dto.request.MessageRequest;
import com.project.mdyshop.exception.ChatException;
import com.project.mdyshop.model.Message;
import com.project.mdyshop.model.Shop;
import com.project.mdyshop.model.User;

import java.util.List;

public interface MessageService {

    Message createUserMessage(User user, MessageRequest request) throws ChatException;

    Message createShopMessage(Shop shop, MessageRequest request) throws ChatException;

    List<Message> findChatMessage(Long chatId) throws ChatException;
}
