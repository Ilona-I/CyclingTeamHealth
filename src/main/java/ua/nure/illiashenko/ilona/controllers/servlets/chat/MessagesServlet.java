package ua.nure.illiashenko.ilona.controllers.servlets.chat;

import org.json.JSONObject;
import ua.nure.illiashenko.ilona.controllers.ResponseWriter;
import ua.nure.illiashenko.ilona.controllers.dto.MessageData;
import ua.nure.illiashenko.ilona.dao.entities.Chat;
import ua.nure.illiashenko.ilona.dao.entities.Message;
import ua.nure.illiashenko.ilona.services.ChatService;
import ua.nure.illiashenko.ilona.services.DataValidator;
import ua.nure.illiashenko.ilona.services.UserService;
import ua.nure.illiashenko.ilona.utils.Base64Util;

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
import static ua.nure.illiashenko.ilona.constants.ContextConstants.BASE_64_UTIL;
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
    private Base64Util base64Util;

    @Override
    public void init() {
        chatService = (ChatService) getServletContext().getAttribute(CHAT_SERVICE);
        userService = (UserService) getServletContext().getAttribute(USER_SERVICE);
        responseWriter = (ResponseWriter) getServletContext().getAttribute(RESPONSE_WRITER);
        dataValidator = (DataValidator) getServletContext().getAttribute(DATA_VALIDATOR);
        base64Util = (Base64Util) getServletContext().getAttribute(BASE_64_UTIL);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        MessageData messageData = new MessageData(request);
        System.out.println(messageData);
        List<String> validationErrors = dataValidator.validate(messageData);
        System.out.println(validationErrors);
        if (!validationErrors.isEmpty()) {
            responseWriter.writeValidationErrors(response, validationErrors);
            return;
        }
        Message message = new Message();
        message.setChatId(Integer.parseInt(messageData.getChatId()));
        message.setSender(messageData.getSender());
        message.setDateTime(new Timestamp(new Date().getTime()));
        message.setText(messageData.getText());
        chatService.sendMessage(message);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String chatIdString = request.getParameter(CHAT_ID);
        int chatId;
        if (chatIdString == null) {
            String sender = new JSONObject(base64Util.decodeString(request.getHeader("Authorization"))).get("login").toString();
            String receiver = Objects.requireNonNull(request.getParameter(RECEIVER));
            String chatType = request.getParameter(CHAT_TYPE);
            if (!dataValidator.isLogin(sender) || !dataValidator.isLogin(receiver) || chatType != null && !dataValidator.isChatType(chatType)) {
                System.out.println(83);
                response.setStatus(400);
                return;
            }
            if (!userService.isUserWithSuchLoginExists(sender) || !userService.isUserWithSuchLoginExists(receiver)) {
                System.out.println(88);
                response.setStatus(404);
                return;
            }
            Chat chat;
            Optional<Chat> optionalChatId = chatService.getUsersChat(sender, receiver);
            if (optionalChatId.isPresent()) {
                System.out.println(95);
                chat  = optionalChatId.get();
            } else {
                System.out.println(98);
                if (chatType == null) {
                    chatType = PRIVATE;
                }
                chat = chatService.createChat(sender, receiver, chatType);
            }
            System.out.println("104: "+chat.getName());
            responseWriter.writeChat(response, chat);
            return;
        } else {
            chatId = Integer.parseInt(chatIdString);
        }
        List<String> messages = chatService.getChatMessages(chatId);
        responseWriter.writeMessages(response, messages);
    }
}
