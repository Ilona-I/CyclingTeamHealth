package ua.nure.illiashenko.ilona.controllers.servlets.user;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.illiashenko.ilona.controllers.dto.UserData;
import ua.nure.illiashenko.ilona.dao.entities.User;
import ua.nure.illiashenko.ilona.services.DataValidator;
import ua.nure.illiashenko.ilona.services.TeamService;
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
import static ua.nure.illiashenko.ilona.constants.ContextConstants.TEAM_SERVICE;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.USER_SERVICE;
import static ua.nure.illiashenko.ilona.constants.TeamType.ENTER_TEAM;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(SignUpServlet.class);
    private UserService userService;
    private TeamService teamService;
    private DataValidator dataValidator;

    @Override
    public void init() {
        userService = (UserService) getServletContext().getAttribute(USER_SERVICE);
        teamService = (TeamService) getServletContext().getAttribute(TEAM_SERVICE);
        dataValidator = (DataValidator) getServletContext().getAttribute(DATA_VALIDATOR);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserData userData = new UserData(request);
        List<String> validationErrors = dataValidator.validate(userData);
        if (userService.isUserWithSuchLoginExists(userData.getLogin())) {
            validationErrors.add("userWithSuchLoginExists");
        }
        if (ENTER_TEAM.equals(userData.getTeamType()) && dataValidator.isNumber(userData.getTeamId()) && !teamService.isTeamWithSuchIdExists(Integer.parseInt(userData.getTeamId()))) {
            validationErrors.add("teamDoesNotExist");
            response.setStatus(404);
        }
        if (!validationErrors.isEmpty()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("validationErrors", validationErrors);
            PrintWriter writer = response.getWriter();
            writer.write(jsonObject.toString());
            writer.print(jsonObject);
        } else {
            User user = new User();
            user.setLogin(userData.getLogin());
            user.setPassword(userData.getPassword());
            user.setFirstName(userData.getFirstName());
            user.setLastName(userData.getLastName());
            user.setEmail(userData.getEmail());
            user.setRole(userData.getRole());

            user.setTeamId(Integer.parseInt(userData.getTeamId()));
            user.setBirthDate(Date.valueOf(userData.getBirthDate()));
            user.setHeight(Double.parseDouble(userData.getHeight()));
            user.setWeight(Double.parseDouble(userData.getWeight()));

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("user", user);
            PrintWriter writer = response.getWriter();
            writer.write(jsonObject.toString());
            writer.print(jsonObject);
        }
    }
}
