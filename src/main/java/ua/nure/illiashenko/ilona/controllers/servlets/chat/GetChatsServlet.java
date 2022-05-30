package ua.nure.illiashenko.ilona.controllers.servlets.chat;

import ua.nure.illiashenko.ilona.controllers.ResponseWriter;
import ua.nure.illiashenko.ilona.dao.entities.UserChat;
import ua.nure.illiashenko.ilona.services.ChatService;
import ua.nure.illiashenko.ilona.services.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static ua.nure.illiashenko.ilona.constants.ContextConstants.CHAT_SERVICE;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.RESPONSE_WRITER;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.USER_SERVICE;
import static ua.nure.illiashenko.ilona.constants.UserConstants.LOGIN;

@WebServlet("/chats")
public class GetChatsServlet extends HttpServlet {

    private ChatService chatService;
    private UserService userService;
    private ResponseWriter responseWriter;

    @Override
    public void init() {
        chatService = (ChatService) getServletContext().getAttribute(CHAT_SERVICE);
        responseWriter = (ResponseWriter) getServletContext().getAttribute(RESPONSE_WRITER);
        userService = (UserService) getServletContext().getAttribute(USER_SERVICE);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = Objects.requireNonNull(request.getParameter(LOGIN));
        if (!userService.isUserWithSuchLoginExists(login)) {
            response.setStatus(404);
            return;
        }
        List<UserChat> chats = chatService.getUserChats(login);
        responseWriter.writeUserChats(response, chats);
    }
}