package ua.nure.illiashenko.ilona.controllers.servlets.chat;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.illiashenko.ilona.dao.entities.UserChat;
import ua.nure.illiashenko.ilona.services.ChatService;
import ua.nure.illiashenko.ilona.services.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;

import static ua.nure.illiashenko.ilona.constants.ContextConstants.CHAT_SERVICE;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.USER_SERVICE;

@WebServlet("/chats")
public class GetChatsServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(GetChatsServlet.class);
    private ChatService chatService;
    private UserService userService;

    @Override
    public void init() {
        chatService = (ChatService) getServletContext().getAttribute(CHAT_SERVICE);
        userService = (UserService) getServletContext().getAttribute(USER_SERVICE);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userLogin = Objects.requireNonNull(request.getParameter("userLogin"));
        if (!userService.isUserWithSuchLoginExists(userLogin)) {
            response.setStatus(404);
            return;
        }
        List<UserChat> chats = chatService.getUserChats(userLogin);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userChats", chats);
        PrintWriter writer = response.getWriter();
        writer.write(jsonObject.toString());
        writer.print(jsonObject);
    }
}