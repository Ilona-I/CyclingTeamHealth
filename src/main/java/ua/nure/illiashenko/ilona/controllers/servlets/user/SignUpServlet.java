package ua.nure.illiashenko.ilona.controllers.servlets.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.illiashenko.ilona.services.DataValidator;
import ua.nure.illiashenko.ilona.services.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ua.nure.illiashenko.ilona.constants.ContextConstants.DATA_VALIDATOR;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.USER_SERVICE;

@WebServlet
public class SignUpServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(SignUpServlet.class);
    private UserService userService;
    private DataValidator dataValidator;

    @Override
    public void init() {
        userService = (UserService) getServletContext().getAttribute(USER_SERVICE);
        dataValidator = (DataValidator) getServletContext().getAttribute(DATA_VALIDATOR);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {

    }
}
