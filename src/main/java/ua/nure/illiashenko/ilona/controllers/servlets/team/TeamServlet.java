package ua.nure.illiashenko.ilona.controllers.servlets.team;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.illiashenko.ilona.controllers.ResponseWriter;
import ua.nure.illiashenko.ilona.controllers.dto.TeamData;
import ua.nure.illiashenko.ilona.dao.entities.Team;
import ua.nure.illiashenko.ilona.services.DataValidator;
import ua.nure.illiashenko.ilona.services.TeamService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;

import static ua.nure.illiashenko.ilona.constants.ContextConstants.DATA_VALIDATOR;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.RESPONSE_WRITER;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.TEAM_SERVICE;

@WebServlet("/team")
public class TeamServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(TeamServlet.class);
    private TeamService teamService;
    private ResponseWriter responseWriter;
    private DataValidator dataValidator;

    @Override
    public void init() {
        teamService = (TeamService) getServletContext().getAttribute(TEAM_SERVICE);
        responseWriter = (ResponseWriter) getServletContext().getAttribute(RESPONSE_WRITER);
        dataValidator = (DataValidator) getServletContext().getAttribute(DATA_VALIDATOR);
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TeamData teamData = new TeamData(request);
        List<String> validationErrors = dataValidator.validate(teamData);
        if (!validationErrors.isEmpty()) {
            responseWriter.writeValidationErrors(response, validationErrors);
            return;
        }
        Team team = new Team();
        team.setId(Integer.parseInt(teamData.getId()));
        team.setName(teamData.getName());
        teamService.updateTeam(team.getId(), team);
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) {
        String teamId = Objects.requireNonNull(request.getParameter("teamId"));
        if (!dataValidator.isNumber(teamId)) {
            response.setStatus(400);
            return;
        }
        teamService.deleteTeam(Integer.parseInt(teamId));
    }
}
