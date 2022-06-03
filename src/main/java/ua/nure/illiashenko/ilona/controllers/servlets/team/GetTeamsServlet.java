package ua.nure.illiashenko.ilona.controllers.servlets.team;

import org.json.JSONObject;
import ua.nure.illiashenko.ilona.controllers.ResponseWriter;
import ua.nure.illiashenko.ilona.dao.entities.Team;
import ua.nure.illiashenko.ilona.services.TeamService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

import static ua.nure.illiashenko.ilona.constants.ContextConstants.RESPONSE_WRITER;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.TEAM_SERVICE;

@WebServlet("/teams")
public class GetTeamsServlet extends HttpServlet {

    private TeamService teamService;
    private ResponseWriter responseWriter;

    @Override
    public void init() {
        teamService = (TeamService) getServletContext().getAttribute(TEAM_SERVICE);
        responseWriter = (ResponseWriter) getServletContext().getAttribute(RESPONSE_WRITER);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Integer> teams = teamService.getTeamsRatings();
        responseWriter.writeTeamsRatings(response, teams);
    }
}
