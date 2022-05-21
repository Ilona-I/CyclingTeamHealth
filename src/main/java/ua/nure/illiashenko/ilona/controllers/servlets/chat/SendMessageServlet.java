package ua.nure.illiashenko.ilona.controllers.servlets.chat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.illiashenko.ilona.services.ChatService;
import ua.nure.illiashenko.ilona.services.DataValidator;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ua.nure.illiashenko.ilona.constants.ContextConstants.CHAT_SERVICE;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.DATA_VALIDATOR;

@WebServlet("/chat")
public class SendMessageServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(SendMessageServlet.class);
    private ChatService chatService;
    private DataValidator dataValidator;

    @Override
    public void init() {
        chatService = (ChatService) getServletContext().getAttribute(CHAT_SERVICE);
        dataValidator = (DataValidator) getServletContext().getAttribute(DATA_VALIDATOR);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){

    }
}
