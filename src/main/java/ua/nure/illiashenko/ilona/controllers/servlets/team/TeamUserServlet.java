package ua.nure.illiashenko.ilona.controllers.servlets.team;

import ua.nure.illiashenko.ilona.dao.entities.User;
import ua.nure.illiashenko.ilona.services.DataValidator;
import ua.nure.illiashenko.ilona.services.TeamService;
import ua.nure.illiashenko.ilona.services.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Objects;

import static ua.nure.illiashenko.ilona.constants.ContextConstants.DATA_VALIDATOR;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.TEAM_SERVICE;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.USER_SERVICE;

@WebServlet("/team/user")
public class TeamUserServlet extends HttpServlet {

    private TeamService teamService;
    private UserService userService;
    private DataValidator dataValidator;

    @Override
    public void init() {
        teamService = (TeamService) getServletContext().getAttribute(TEAM_SERVICE);
        userService = (UserService) getServletContext().getAttribute(USER_SERVICE);
        dataValidator = (DataValidator) getServletContext().getAttribute(DATA_VALIDATOR);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        String teamIdString = Objects.requireNonNull(request.getParameter("teamId"));
        String login = Objects.requireNonNull(request.getParameter("login"));
        if (!dataValidator.isNumber(teamIdString)) {
            response.setStatus(400);
            return;
        }
        int teamId = Integer.parseInt(teamIdString);
        if (!teamService.isTeamWithSuchIdExists(teamId) || !userService.isUserWithSuchLoginExists(login)) {
            response.setStatus(404);
            return;
        }
        User user = userService.getUser(login).get();
        user.setTeamId(teamId);
        userService.updateUser(login, user);
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
