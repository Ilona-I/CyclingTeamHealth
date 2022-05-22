package ua.nure.illiashenko.ilona.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.illiashenko.ilona.dao.ChatDAO;
import ua.nure.illiashenko.ilona.dao.MessageDAO;
import ua.nure.illiashenko.ilona.dao.UserChatDAO;

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
}
