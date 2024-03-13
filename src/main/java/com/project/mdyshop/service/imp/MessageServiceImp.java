package com.project.mdyshop.service.imp;

import com.project.mdyshop.dto.request.MessageRequest;
import com.project.mdyshop.exception.ChatException;
import com.project.mdyshop.model.Chat;
import com.project.mdyshop.model.Message;
import com.project.mdyshop.model.Shop;
import com.project.mdyshop.model.User;
import com.project.mdyshop.repository.ChatRepository;
import com.project.mdyshop.repository.MessageRepository;
import com.project.mdyshop.repository.ShopRepository;
import com.project.mdyshop.repository.UserRepository;
import com.project.mdyshop.service.ChatService;
import com.project.mdyshop.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImp implements MessageService {
    @Autowired
    ChatService chatService;
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    ChatRepository chatRepository;
    @Autowired
    ShopRepository shopRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public Message createUserMessage(User user, MessageRequest request) throws ChatException{
        Message message = new Message();
        Chat chat = chatRepository.findChatByUserAndShop(user.getId(), request.getReceiptedId());

        if (chat == null) {
            Optional<Shop> opt = shopRepository.findById(request.getReceiptedId());

            if (opt.isPresent()) {
                Shop shop = opt.get();
                Chat chat1 = chatService.createChat(user, shop);

                message.setChat(chat1);
                message.setContent(request.getContent());
                message.setSenderId(user.getId());
                message.setReceiptedId(request.getReceiptedId());
                message.setTimeStamp(LocalDateTime.now());

                Message saveMessage = messageRepository.save(message);
                chat1.getMessages().add(saveMessage);
                chat1.setLastMessage(request.getContent());

                chatRepository.save(chat1);
                return saveMessage;
            }
        }
        else {
            message.setChat(chat);
            message.setSenderId(user.getId());
            message.setReceiptedId(request.getReceiptedId());
            message.setContent(request.getContent());
            message.setTimeStamp(LocalDateTime.now());

            Message saveMessage = messageRepository.save(message);
            chat.getMessages().add(saveMessage);
            chat.setLastMessage(request.getContent());

            chatRepository.save(chat);
            return saveMessage;
        }

        return null;
    }

    @Override
    public Message createShopMessage(Shop shop, MessageRequest request) throws ChatException{
        Message message = new Message();
        Chat chat = chatRepository.findChatByUserAndShop(request.getReceiptedId(), shop.getId());

        if (chat == null) {
            Optional<User> opt = userRepository.findById(request.getReceiptedId());

            if (opt.isPresent()) {
                User user = opt.get();
                Chat chat1 = chatService.createChat(user, shop);

                message.setChat(chat1);
                message.setContent(request.getContent());
                message.setSenderId(shop.getId());
                message.setReceiptedId(request.getReceiptedId());
                message.setTimeStamp(LocalDateTime.now());

                Message saveMessage = messageRepository.save(message);
                chat1.getMessages().add(saveMessage);
                chat1.setLastMessage(request.getContent());

                chatRepository.save(chat1);
                return saveMessage;
            }
        }
        else {
            message.setChat(chat);
            message.setSenderId(shop.getId());
            message.setReceiptedId(request.getReceiptedId());
            message.setContent(request.getContent());
            message.setTimeStamp(LocalDateTime.now());

            Message saveMessage = messageRepository.save(message);
            chat.getMessages().add(saveMessage);
            chat.setLastMessage(request.getContent());

            chatRepository.save(chat);
            return saveMessage;
        }

        return null;
    }

    @Override
    public List<Message> findChatMessage(Long chatId) throws ChatException {
        return messageRepository.findByChatId(chatId);
    }
}
