package ua.nure.illiashenko.ilona.controllers.servlets.user;

import org.json.JSONObject;
import ua.nure.illiashenko.ilona.controllers.ResponseWriter;
import ua.nure.illiashenko.ilona.controllers.dto.UserData;
import ua.nure.illiashenko.ilona.dao.entities.User;
import ua.nure.illiashenko.ilona.services.DataValidator;
import ua.nure.illiashenko.ilona.services.UserService;
import ua.nure.illiashenko.ilona.utils.Base64Util;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

import static ua.nure.illiashenko.ilona.constants.ContextConstants.BASE_64_UTIL;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.DATA_VALIDATOR;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.RESPONSE_WRITER;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.USER_SERVICE;
import static ua.nure.illiashenko.ilona.constants.UserConstants.LOGIN;

@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private UserService userService;
    private DataValidator dataValidator;
    private ResponseWriter responseWriter;
    private Base64Util base64Util;

    @Override
    public void init() {
        userService = (UserService) getServletContext().getAttribute(USER_SERVICE);
        responseWriter = (ResponseWriter) getServletContext().getAttribute(RESPONSE_WRITER);
        dataValidator = (DataValidator) getServletContext().getAttribute(DATA_VALIDATOR);
        base64Util = (Base64Util) getServletContext().getAttribute(BASE_64_UTIL);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = Objects.requireNonNull(request.getParameter(LOGIN));
        if (!userService.isUserWithSuchLoginExists(login)) {
            response.setStatus(404);
            return;
        }
        User user = userService.getUser(login).get();
        responseWriter.writeUser(response, user);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println(56);
        UserData userData = new UserData(request);
        System.out.println(userData);
        List<String> validationErrors = dataValidator.validate(userData);
        System.out.println(validationErrors);
        if (!validationErrors.isEmpty()) {
            responseWriter.writeValidationErrors(response, validationErrors);
            return;
        }
        boolean isCurrentUser = userData.getLogin().equals(new JSONObject(base64Util.decodeString(request.getHeader("Authorization"))).get("login"));
        User user = userService.getUser(userData.getLogin()).get();
        System.out.println("66: "+user);
        user.setFirstName(userData.getFirstName());
        user.setLastName(userData.getLastName());
        user.setEmail(userData.getEmail());
        if (!userData.getBirthDate().isEmpty()) {
            user.setBirthDate(Date.valueOf(userData.getBirthDate()));
        }
        if (!userData.getHeight().isEmpty()) {
            user.setHeight(Double.parseDouble(userData.getHeight()));
        }
        if (!userData.getWeight().isEmpty()) {
            user.setWeight(Double.parseDouble(userData.getWeight()));
        }
        user.setStatus(userData.getStatus());
        userService.updateUser(user.getLogin(), user);
        if (isCurrentUser) {
            response.setHeader("Authorization", base64Util.encodeString(user.toString()));
            return;
        }
        responseWriter.writeUser(response, user);
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) {
        String login = Objects.requireNonNull(request.getParameter("login"));
        if (!userService.isUserWithSuchLoginExists(login)) {
            response.setStatus(404);
            return;
        }
        userService.deleteUser(login);
    }
}
