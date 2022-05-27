package ua.nure.illiashenko.ilona.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.illiashenko.ilona.dao.ChatDAO;
import ua.nure.illiashenko.ilona.dao.MessageDAO;
import ua.nure.illiashenko.ilona.dao.UserChatDAO;
import ua.nure.illiashenko.ilona.dao.entities.UserChat;

import java.util.List;

import static ua.nure.illiashenko.ilona.constants.SQLQuery.GET_USER_CHATS;

public class ChatService {

    private static final Logger logger = LoggerFactory.getLogger(ChatService.class);
    private final ChatDAO chatDAO;
    private final UserChatDAO userChatDAO;
    private final MessageDAO messageDAO;
    private final TransactionManager transactionManager;

    public ChatService(ChatDAO chatDAO, UserChatDAO userChatDAO, MessageDAO messageDAO, TransactionManager transactionManager){
        this.chatDAO = chatDAO;
        this.userChatDAO = userChatDAO;
        this.messageDAO = messageDAO;
        this.transactionManager = transactionManager;
    }
/*
    public List<UserChat> getUserChats(String userLogin){

    }*/
}
