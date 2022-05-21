package ua.nure.illiashenko.ilona.controllers.servlets.team;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.illiashenko.ilona.services.DataValidator;
import ua.nure.illiashenko.ilona.services.TeamService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ua.nure.illiashenko.ilona.constants.ContextConstants.DATA_VALIDATOR;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.TEAM_SERVICE;

@WebServlet("/team")
public class UpdateTeamServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(UpdateTeamServlet.class);
    private TeamService teamService;
    private DataValidator dataValidator;

    @Override
    public void init() {
        teamService = (TeamService) getServletContext().getAttribute(TEAM_SERVICE);
        dataValidator = (DataValidator) getServletContext().getAttribute(DATA_VALIDATOR);
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response){

    }
}
