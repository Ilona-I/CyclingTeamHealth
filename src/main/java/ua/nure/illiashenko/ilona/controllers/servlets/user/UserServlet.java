package ua.nure.illiashenko.ilona.controllers.servlets.user;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.illiashenko.ilona.controllers.dto.UserData;
import ua.nure.illiashenko.ilona.dao.entities.User;
import ua.nure.illiashenko.ilona.services.DataValidator;
import ua.nure.illiashenko.ilona.services.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import static ua.nure.illiashenko.ilona.constants.ContextConstants.DATA_VALIDATOR;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.USER_SERVICE;
@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(UserServlet.class);
    private UserService userService;
    private DataValidator dataValidator;

    @Override
    public void init() {
        userService = (UserService) getServletContext().getAttribute(USER_SERVICE);
        dataValidator = (DataValidator) getServletContext().getAttribute(DATA_VALIDATOR);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserData userData = new UserData(request);
        List<String> validationErrors = dataValidator.validate(userData);
        if (!validationErrors.isEmpty()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("validationErrors", validationErrors);
            PrintWriter writer = response.getWriter();
            writer.write(jsonObject.toString());
            writer.print(jsonObject);
        } else {
            User user = new User();
            user.setLogin(userData.getLogin());
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

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("user", user);
            PrintWriter writer = response.getWriter();
            writer.write(jsonObject.toString());
            writer.print(jsonObject);
        }
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) {

    }
}
