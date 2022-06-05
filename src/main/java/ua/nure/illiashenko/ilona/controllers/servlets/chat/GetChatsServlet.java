package ua.nure.illiashenko.ilona.controllers.servlets.chat;

import org.json.JSONObject;
import ua.nure.illiashenko.ilona.controllers.ResponseWriter;
import ua.nure.illiashenko.ilona.dao.entities.UserChat;
import ua.nure.illiashenko.ilona.services.ChatService;
import ua.nure.illiashenko.ilona.services.UserService;
import ua.nure.illiashenko.ilona.utils.Base64Util;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static ua.nure.illiashenko.ilona.constants.ContextConstants.BASE_64_UTIL;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.CHAT_SERVICE;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.RESPONSE_WRITER;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.USER_SERVICE;

@WebServlet("/chats")
public class GetChatsServlet extends HttpServlet {

    private ChatService chatService;
    private UserService userService;
    private ResponseWriter responseWriter;
    private Base64Util base64Util;

    @Override
    public void init() {
        chatService = (ChatService) getServletContext().getAttribute(CHAT_SERVICE);
        responseWriter = (ResponseWriter) getServletContext().getAttribute(RESPONSE_WRITER);
        userService = (UserService) getServletContext().getAttribute(USER_SERVICE);
        base64Util = (Base64Util) getServletContext().getAttribute(BASE_64_UTIL);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = new JSONObject(base64Util.decodeString(request.getHeader("Authorization"))).get("login").toString();
        if (!userService.isUserWithSuchLoginExists(login)) {
            response.setStatus(404);
            return;
        }
        Map<Integer, String> chats = chatService.getUserChats(login);
        responseWriter.writeUserChats(response, chats);
    }
}