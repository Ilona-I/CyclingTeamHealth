package ua.nure.illiashenko.ilona.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.illiashenko.ilona.dao.ChatDAO;
import ua.nure.illiashenko.ilona.dao.MessageDAO;
import ua.nure.illiashenko.ilona.dao.UserChatDAO;
import ua.nure.illiashenko.ilona.dao.entities.Chat;
import ua.nure.illiashenko.ilona.dao.entities.Message;
import ua.nure.illiashenko.ilona.dao.entities.UserChat;
import ua.nure.illiashenko.ilona.exceptions.CannotDoTransactionException;
import ua.nure.illiashenko.ilona.exceptions.chat.CannotFindChatMessagesException;
import ua.nure.illiashenko.ilona.exceptions.chat.CannotFindUserChatsException;
import ua.nure.illiashenko.ilona.exceptions.chat.CannotSendMessageException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class ChatService {

    private static final Logger logger = LoggerFactory.getLogger(ChatService.class);
    private final ChatDAO chatDAO;
    private final UserChatDAO userChatDAO;
    private final MessageDAO messageDAO;
    private final TransactionManager transactionManager;

    public ChatService(ChatDAO chatDAO, UserChatDAO userChatDAO, MessageDAO messageDAO, TransactionManager transactionManager) {
        this.chatDAO = chatDAO;
        this.userChatDAO = userChatDAO;
        this.messageDAO = messageDAO;
        this.transactionManager = transactionManager;
    }

    public boolean sendMessage(Message message) {
        Function<Connection, Boolean> function = connection -> {
            try {
                messageDAO.insert(message, connection);
                return true;
            } catch (SQLException e) {
                throw new CannotSendMessageException(e.getMessage());
            }
        };
        return doInTransaction(function);
    }

    public Chat createChat(String sender, String receiver, String chatType) {
        Function<Connection, Chat> function = connection -> {
            try {
                Chat chat = new Chat();
                chat.setType(chatType);
                chat = chatDAO.insert(chat, connection);
                UserChat userChat = new UserChat();
                userChat.setChatId(chat.getId());
                userChat.setLogin(sender);
                userChatDAO.insert(userChat, connection);
                userChat.setLogin(receiver);
                userChatDAO.insert(userChat, connection);
                return chat;
            } catch (SQLException e) {
                throw new CannotSendMessageException(e.getMessage());
            }
        };
        return doInTransaction(function);
    }

    public Optional<Integer> getUsersChatId(String sender, String receiver) {
        Function<Connection, Optional<Integer>> function = connection -> {
            try {
                return userChatDAO.getUsersChatId(sender, receiver, connection);
            } catch (SQLException e) {
                throw new CannotFindUserChatsException(e.getMessage());
            }
        };
        return doInTransaction(function);
    }


    public List<UserChat> getUserChats(String userLogin) {
        Function<Connection, List<UserChat>> function = connection -> {
            try {
                return userChatDAO.getUserChats(userLogin, connection);
            } catch (SQLException e) {
                throw new CannotFindUserChatsException(e.getMessage());
            }
        };
        return doInTransaction(function);
    }

    public List<Message> getChatMessages(int chatId) {
        Function<Connection, List<Message>> function = connection -> {
            try {
                return messageDAO.getChatMessages(chatId, connection);
            } catch (SQLException e) {
                throw new CannotFindChatMessagesException(e.getMessage());
            }
        };
        return doInTransaction(function);
    }

    private <R> R doInTransaction(Function<Connection, R> function) {
        try {
            return transactionManager.doInTransaction(function);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new CannotDoTransactionException(e.getMessage());
        }
    }
}
