package com.project.mdyshop.service;

import com.project.mdyshop.dto.request.CreateChatRequest;
import com.project.mdyshop.exception.ChatException;
import com.project.mdyshop.model.Chat;

import java.util.List;

public interface ChatService {

    Chat createChat(CreateChatRequest request);

    Chat findChatById(Long chatId) throws ChatException;

    List<Chat> findUserChats(Long userId);

    List<Chat> findShopChats(Long shopId);
}
