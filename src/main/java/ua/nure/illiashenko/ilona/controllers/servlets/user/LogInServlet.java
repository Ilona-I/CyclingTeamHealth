package ua.nure.illiashenko.ilona.controllers.servlets.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.illiashenko.ilona.controllers.ResponseWriter;
import ua.nure.illiashenko.ilona.controllers.dto.LogInData;
import ua.nure.illiashenko.ilona.dao.entities.User;
import ua.nure.illiashenko.ilona.services.DataValidator;
import ua.nure.illiashenko.ilona.services.UserService;
import ua.nure.illiashenko.ilona.utils.MD5Util;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import static ua.nure.illiashenko.ilona.constants.ContextConstants.DATA_VALIDATOR;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.MD_5_UTIL;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.RESPONSE_WRITER;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.USER_SERVICE;

@WebServlet("/logIn")
public class LogInServlet extends HttpServlet {

    private final Logger logger = LoggerFactory.getLogger(LogInServlet.class);
    private UserService userService;
    private ResponseWriter responseWriter;
    private DataValidator dataValidator;
    private MD5Util md5Util;

    @Override
    public void init() {
        userService = (UserService) getServletContext().getAttribute(USER_SERVICE);
        responseWriter = (ResponseWriter) getServletContext().getAttribute(RESPONSE_WRITER);
        dataValidator = (DataValidator) getServletContext().getAttribute(DATA_VALIDATOR);
        md5Util = (MD5Util) getServletContext().getAttribute(MD_5_UTIL);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LogInData logInData = new LogInData(request);
        List<String> validationErrors = dataValidator.validate(logInData);
        if (!validationErrors.isEmpty()) {
            responseWriter.writeValidationErrors(response, validationErrors);
            return;
        }
        Optional<User> optionalUser = userService.getUser(logInData.getLogin());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            try {
                if (!user.getPassword().equals(md5Util.md5(logInData.getPassword()))) {
                    validationErrors.add("wrongPassword");
                    responseWriter.writeValidationErrors(response, validationErrors);
                    return;
                }
            } catch (NoSuchAlgorithmException e) {
                logger.error(e.getMessage());
            }
            responseWriter.writeUser(response, user);
            return;
        }
        responseWriter.writeValidationErrors(response, validationErrors);
    }
}
