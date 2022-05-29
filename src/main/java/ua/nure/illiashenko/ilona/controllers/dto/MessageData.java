package ua.nure.illiashenko.ilona.controllers.dto;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static ua.nure.illiashenko.ilona.constants.ParameterConstants.CHAT_ID;
import static ua.nure.illiashenko.ilona.constants.ParameterConstants.SENDER;
import static ua.nure.illiashenko.ilona.constants.ParameterConstants.TEXT;

public class MessageData {

    private final String chatId;
    private final String sender;
    private final String text;

    public MessageData(HttpServletRequest request) {
        this.chatId = Objects.requireNonNull(request.getParameter(CHAT_ID));
        this.sender = Objects.requireNonNull(request.getParameter(SENDER));
        this.text = Objects.requireNonNull(request.getParameter(TEXT));
    }

    public String getChatId() {
        return chatId;
    }

    public String getSender() {
        return sender;
    }

    public String getText() {
        return text;
    }
}
