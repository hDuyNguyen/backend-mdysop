package com.project.mdyshop.service.imp;

import com.project.mdyshop.exception.ChatException;
import com.project.mdyshop.model.Chat;
import com.project.mdyshop.model.Message;
import com.project.mdyshop.model.Shop;
import com.project.mdyshop.model.User;
import com.project.mdyshop.repository.ChatRepository;
import com.project.mdyshop.repository.MessageRepository;
import com.project.mdyshop.service.ChatService;
import com.project.mdyshop.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImp implements MessageService {
    @Autowired
    ChatService chatService;
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    ChatRepository chatRepository;

    @Override
    public Message createUserMessage(User user, Long chatId, Message message) throws ChatException{
        Message message1 = new Message();
        Chat chat = chatService.findChatById(chatId);

        message1.setSenderId(user.getId());
        message1.setChat(chat);
        message1.setContent(message.getContent());
        message1.setTimeStamp(LocalDateTime.now());

        Message saveMessage = messageRepository.save(message1);

        chat.getMessages().add(saveMessage);
        chatRepository.save(chat);
        return saveMessage;
    }

    @Override
    public Message createShopMessage(Shop shop, Long chatId, Message message) throws ChatException{
        Message message1 = new Message();
        Chat chat = chatService.findChatById(chatId);

        message1.setSenderId(shop.getId());
        message1.setChat(chat);
        message1.setContent(message.getContent());
        message1.setTimeStamp(LocalDateTime.now());

        Message saveMessage = messageRepository.save(message1);

        chat.getMessages().add(saveMessage);
        chatRepository.save(chat);
        return saveMessage;
    }

    @Override
    public List<Message> findChatMessage(Long chatId) throws ChatException {
        return messageRepository.findByChatId(chatId);
    }
}
