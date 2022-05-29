package ua.nure.illiashenko.ilona.controllers.servlets.user;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.illiashenko.ilona.controllers.dto.RegistrationData;
import ua.nure.illiashenko.ilona.dao.entities.Team;
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
import static ua.nure.illiashenko.ilona.constants.StatusType.ACTIVE;
import static ua.nure.illiashenko.ilona.constants.TeamType.ENTER_TEAM;
import static ua.nure.illiashenko.ilona.constants.TeamType.NEW_TEAM;

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
        RegistrationData registrationData = new RegistrationData(request);
        List<String> validationErrors = dataValidator.validate(registrationData);
        if (userService.isUserWithSuchLoginExists(registrationData.getLogin())) {
            validationErrors.add("userWithSuchLoginExists");
        }
        if (ENTER_TEAM.equals(registrationData.getTeamType()) && dataValidator.isNumber(registrationData.getTeamId()) && !teamService.isTeamWithSuchIdExists(Integer.parseInt(registrationData.getTeamId()))) {
            validationErrors.add("teamDoesNotExist");
            response.setStatus(404);
        }
        if (!validationErrors.isEmpty()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("validationErrors", validationErrors);
            PrintWriter writer = response.getWriter();
            writer.write(jsonObject.toString());
            writer.print(jsonObject);
            return;
        }
        User user = new User();
        user.setLogin(registrationData.getLogin());
        user.setPassword(registrationData.getPassword());
        user.setFirstName(registrationData.getFirstName());
        user.setLastName(registrationData.getLastName());
        user.setEmail(registrationData.getEmail());
        user.setRole(registrationData.getRole());
        if (NEW_TEAM.equals(registrationData.getTeamType())) {
            Team team = new Team();
            team.setName(registrationData.getTeamId());
            team = teamService.createTeam(team);
            user.setTeamId(team.getId());
        } else {
            user.setTeamId(Integer.parseInt(registrationData.getTeamId()));
        }
        if (!registrationData.getBirthDate().isEmpty()) {
            user.setBirthDate(Date.valueOf(registrationData.getBirthDate()));
        }
        if (!registrationData.getHeight().isEmpty()) {
            user.setHeight(Double.parseDouble(registrationData.getHeight()));
        }
        if (!registrationData.getWeight().isEmpty()) {
            user.setWeight(Double.parseDouble(registrationData.getWeight()));
        }

        user.setStatus(ACTIVE);
        userService.addUser(user);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user", user);
        PrintWriter writer = response.getWriter();
        writer.write(jsonObject.toString());
        writer.print(jsonObject);

    }
}
