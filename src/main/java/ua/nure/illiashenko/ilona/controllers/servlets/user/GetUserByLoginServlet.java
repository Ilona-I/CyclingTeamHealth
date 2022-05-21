package ua.nure.illiashenko.ilona.controllers.servlets.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.illiashenko.ilona.services.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ua.nure.illiashenko.ilona.constants.ContextConstants.USER_SERVICE;

@WebServlet("/user/login")
public class GetUserByLoginServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(GetUserByLoginServlet.class);
    private UserService userService;

    @Override
    public void init() {
        userService = (UserService) getServletContext().getAttribute(USER_SERVICE);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {

    }
}
