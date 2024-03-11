package com.project.mdyshop.service.imp;

import com.project.mdyshop.dto.request.CreateChatRequest;
import com.project.mdyshop.exception.ChatException;
import com.project.mdyshop.model.Chat;
import com.project.mdyshop.repository.ChatRepository;
import com.project.mdyshop.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ChatServiceImp implements ChatService {

    @Autowired
    ChatRepository chatRepository;
    @Override
    public Chat createChat(CreateChatRequest request) {
        Chat isExist = chatRepository.findChatByUserAndShop(request.getUser().getId(), request.getShop().getId());
        if (isExist != null) {
            return isExist;
        }
        else {
            Chat chat = new Chat();
            chat.setUser(request.getUser());
            chat.setShop(request.getShop());
            chat.setTimeStamp(LocalDateTime.now());

            return chatRepository.save(chat);
        }
    }

    @Override
    public Chat findChatById(Long chatId) throws ChatException {
        Optional<Chat> opt = chatRepository.findById(chatId);

        if (opt.isPresent()) {
            return opt.get();
        }
        throw new ChatException("Chat not found with ID: " + chatId);
    }

    @Override
    public List<Chat> findUserChats(Long userId) {

        return chatRepository.findUserChats(userId);
    }

    @Override
    public List<Chat> findShopChats(Long shopId) {

        return chatRepository.finShopChats(shopId);
    }
}
