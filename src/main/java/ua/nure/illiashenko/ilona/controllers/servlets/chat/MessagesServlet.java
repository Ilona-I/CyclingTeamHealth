package ua.nure.illiashenko.ilona.controllers.servlets.chat;

import ua.nure.illiashenko.ilona.controllers.ResponseWriter;
import ua.nure.illiashenko.ilona.controllers.dto.MessageData;
import ua.nure.illiashenko.ilona.dao.entities.Chat;
import ua.nure.illiashenko.ilona.dao.entities.Message;
import ua.nure.illiashenko.ilona.services.ChatService;
import ua.nure.illiashenko.ilona.services.DataValidator;
import ua.nure.illiashenko.ilona.services.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static ua.nure.illiashenko.ilona.constants.ChatType.PRIVATE;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.CHAT_SERVICE;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.DATA_VALIDATOR;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.RESPONSE_WRITER;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.USER_SERVICE;
import static ua.nure.illiashenko.ilona.constants.ParameterConstants.CHAT_ID;
import static ua.nure.illiashenko.ilona.constants.ParameterConstants.CHAT_TYPE;
import static ua.nure.illiashenko.ilona.constants.ParameterConstants.RECEIVER;
import static ua.nure.illiashenko.ilona.constants.ParameterConstants.SENDER;

@WebServlet("/chat")
public class MessagesServlet extends HttpServlet {

    private ChatService chatService;
    private UserService userService;
    private DataValidator dataValidator;
    private ResponseWriter responseWriter;

    @Override
    public void init() {
        chatService = (ChatService) getServletContext().getAttribute(CHAT_SERVICE);
        userService = (UserService) getServletContext().getAttribute(USER_SERVICE);
        responseWriter = (ResponseWriter) getServletContext().getAttribute(RESPONSE_WRITER);
        dataValidator = (DataValidator) getServletContext().getAttribute(DATA_VALIDATOR);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        MessageData messageData = new MessageData(request);
        List<String> validationErrors = dataValidator.validate(messageData);
        if (!validationErrors.isEmpty()) {
            responseWriter.writeValidationErrors(response, validationErrors);
            return;
        }
        Message message = new Message();
        message.setChatId(Integer.parseInt(messageData.getChatId()));
        message.setSender(messageData.getSender());
        message.setDateTime(new Timestamp(new Date().getTime()));
        message.setText(message.getText());
        chatService.sendMessage(message);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String chatIdString = request.getParameter(CHAT_ID);
        int chatId;
        if (chatIdString == null) {
            String sender = Objects.requireNonNull(request.getParameter(SENDER));
            String receiver = Objects.requireNonNull(request.getParameter(RECEIVER));
            String chatType = request.getParameter(CHAT_TYPE);
            if (!dataValidator.isLogin(sender) || !dataValidator.isLogin(receiver) || chatType != null && !dataValidator.isChatType(chatType)) {
                response.setStatus(400);
                return;
            }
            if (!userService.isUserWithSuchLoginExists(sender) || !userService.isUserWithSuchLoginExists(receiver)) {
                response.setStatus(404);
                return;
            }
            Optional<Integer> optionalChatId = chatService.getUsersChatId(sender, receiver);
            if (optionalChatId.isPresent()) {
                chatId = optionalChatId.get();
            } else {
                if (chatType == null) {
                    chatType = PRIVATE;
                }
                Chat chat = chatService.createChat(sender, receiver, chatType);
                chatId = chat.getId();
            }
        } else {
            chatId = Integer.parseInt(chatIdString);
        }
        List<Message> messages = chatService.getChatMessages(chatId);
        responseWriter.writeMessages(response, messages);
    }
}
