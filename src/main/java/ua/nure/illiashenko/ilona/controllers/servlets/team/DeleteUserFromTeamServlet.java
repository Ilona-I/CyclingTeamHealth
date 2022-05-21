package ua.nure.illiashenko.ilona.controllers.servlets.team;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.illiashenko.ilona.services.TeamService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ua.nure.illiashenko.ilona.constants.ContextConstants.TEAM_SERVICE;

@WebServlet("/team/user")
public class DeleteUserFromTeamServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(DeleteUserFromTeamServlet.class);
    private TeamService teamService;

    @Override
    public void init() {
        teamService = (TeamService) getServletContext().getAttribute(TEAM_SERVICE);
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response){

    }
}
