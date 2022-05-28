package ua.nure.illiashenko.ilona.controllers.servlets.chat;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.illiashenko.ilona.dao.entities.Message;
import ua.nure.illiashenko.ilona.services.ChatService;
import ua.nure.illiashenko.ilona.services.DataValidator;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import static ua.nure.illiashenko.ilona.constants.ContextConstants.CHAT_SERVICE;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.DATA_VALIDATOR;

@WebServlet("/chat")
public class MessagesServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(MessagesServlet.class);
    private ChatService chatService;
    private DataValidator dataValidator;

    @Override
    public void init() {
        chatService = (ChatService) getServletContext().getAttribute(CHAT_SERVICE);
        dataValidator = (DataValidator) getServletContext().getAttribute(DATA_VALIDATOR);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        String chatType = Objects.requireNonNull(request.getParameter("chatType"));
        Message message = new Message();
        message.setChatId(Integer.parseInt(Objects.requireNonNull(request.getParameter("chatId"))));
        message.setSender(Objects.requireNonNull(request.getParameter("sender")));
        message.setDateTime(Timestamp.valueOf(Objects.requireNonNull(request.getParameter("datetime"))));
        message.setText(Objects.requireNonNull(request.getParameter("text")));
        chatService.sendMessage(message, chatType);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int chatId = Integer.parseInt(Objects.requireNonNull(request.getParameter("chatId")));
        List<Message> messages = chatService.getChatMessages(chatId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("messages", messages);
        PrintWriter writer = response.getWriter();
        writer.write(jsonObject.toString());
        writer.print(jsonObject);
    }
}
