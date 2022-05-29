package ua.nure.illiashenko.ilona.controllers.servlets.training.results;

import org.json.JSONObject;
import ua.nure.illiashenko.ilona.services.DataValidator;
import ua.nure.illiashenko.ilona.services.TeamService;
import ua.nure.illiashenko.ilona.services.TrainingService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

import static ua.nure.illiashenko.ilona.constants.ContextConstants.DATA_VALIDATOR;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.TEAM_SERVICE;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.TRAINING_SERVICE;

@WebServlet("/trainings/team/results")
public class TeamTrainingsResultsServlet extends HttpServlet {

    private TrainingService trainingService;
    private TeamService teamService;
    private DataValidator dataValidator;

    @Override
    public void init() {
        trainingService = (TrainingService) getServletContext().getAttribute(TRAINING_SERVICE);
        teamService = (TeamService) getServletContext().getAttribute(TEAM_SERVICE);
        dataValidator = (DataValidator) getServletContext().getAttribute(DATA_VALIDATOR);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String teamId = Objects.requireNonNull(request.getParameter("teamId"));
        if (!dataValidator.isNumber(teamId)) {
            response.setStatus(400);
            return;
        }
        int id = Integer.parseInt(teamId);
        if (!teamService.isTeamWithSuchIdExists(id)) {
            response.setStatus(404);
            return;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("teamTrainingsResults", trainingService.getTeamTrainingResults(id));
        PrintWriter writer = response.getWriter();
        writer.write(jsonObject.toString());
        writer.print(jsonObject);
    }
}
