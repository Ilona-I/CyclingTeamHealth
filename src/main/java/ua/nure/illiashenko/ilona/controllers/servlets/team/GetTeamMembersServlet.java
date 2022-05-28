package ua.nure.illiashenko.ilona.controllers.servlets.team;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.illiashenko.ilona.dao.entities.User;
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
import static ua.nure.illiashenko.ilona.constants.ContextConstants.TEAM_SERVICE;

@WebServlet("/team/users")
public class GetTeamMembersServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(GetTeamMembersServlet.class);
    private TeamService teamService;
    private DataValidator dataValidator;

    @Override
    public void init() {
        teamService = (TeamService) getServletContext().getAttribute(TEAM_SERVICE);
        dataValidator = (DataValidator) getServletContext().getAttribute(DATA_VALIDATOR);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String teamId = Objects.requireNonNull(request.getParameter("teamId"));
        if(!dataValidator.isNumber(teamId)){
            response.setStatus(400);
            return;
        }
        List<User> users = teamService.getTeamMembers(Integer.parseInt(teamId));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("teamMembers", users);
        PrintWriter writer = response.getWriter();
        writer.write(jsonObject.toString());
        writer.print(jsonObject);
    }
}
