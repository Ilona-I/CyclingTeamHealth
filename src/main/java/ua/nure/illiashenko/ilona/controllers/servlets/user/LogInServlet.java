package ua.nure.illiashenko.ilona.controllers.servlets.user;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.illiashenko.ilona.controllers.dto.LogInData;
import ua.nure.illiashenko.ilona.dao.entities.User;
import ua.nure.illiashenko.ilona.services.DataValidator;
import ua.nure.illiashenko.ilona.services.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

import static ua.nure.illiashenko.ilona.constants.ContextConstants.DATA_VALIDATOR;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.USER_SERVICE;

@WebServlet("/logIn")
public class LogInServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(LogInServlet.class);
    private UserService userService;
    private DataValidator dataValidator;

    @Override
    public void init() {
        userService = (UserService) getServletContext().getAttribute(USER_SERVICE);
        dataValidator = (DataValidator) getServletContext().getAttribute(DATA_VALIDATOR);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LogInData logInData = new LogInData(request);
        List<String> validationErrors = dataValidator.validate(logInData);
        if (!validationErrors.isEmpty()) {
            setValidationErrors(response, validationErrors);
            return;
        }
        Optional<User> optionalUser = userService.getUser(logInData.getLogin());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (!user.getPassword().equals(logInData.getPassword())) {
                validationErrors.add("wrongPassword");
                setValidationErrors(response, validationErrors);
                return;
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("user", user);
            PrintWriter writer = response.getWriter();
            writer.write(jsonObject.toString());
            writer.print(jsonObject);
            return;
        }
        setValidationErrors(response, validationErrors);
    }

    private void setValidationErrors(HttpServletResponse response, List<String> validationErrors) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("validationErrors", validationErrors);
        PrintWriter writer = response.getWriter();
        writer.write(jsonObject.toString());
        writer.print(jsonObject);
    }
}
