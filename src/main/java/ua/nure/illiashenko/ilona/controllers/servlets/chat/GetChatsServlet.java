package ua.nure.illiashenko.ilona.controllers.servlets.chat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.illiashenko.ilona.dao.entities.Chat;
import ua.nure.illiashenko.ilona.services.ChatService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static ua.nure.illiashenko.ilona.constants.ContextConstants.CHAT_SERVICE;

@WebServlet("/chats")
public class GetChatsServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(GetChatsServlet.class);
    private ChatService chatService;

    @Override
    public void init() {
        chatService = (ChatService) getServletContext().getAttribute(CHAT_SERVICE);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        long chatId = Long.parseLong(Objects.requireNonNull(request.getParameter("chatId")));
       // List<Chat> chats = chatService.g
    }
}