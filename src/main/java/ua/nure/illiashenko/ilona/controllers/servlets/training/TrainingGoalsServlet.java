package ua.nure.illiashenko.ilona.controllers.servlets.training;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.illiashenko.ilona.controllers.ResponseWriter;
import ua.nure.illiashenko.ilona.controllers.dto.TrainingGoalsData;
import ua.nure.illiashenko.ilona.dao.entities.TrainingGoals;
import ua.nure.illiashenko.ilona.services.DataValidator;
import ua.nure.illiashenko.ilona.services.TeamService;
import ua.nure.illiashenko.ilona.services.TrainingService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static ua.nure.illiashenko.ilona.constants.ContextConstants.DATA_VALIDATOR;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.RESPONSE_WRITER;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.TEAM_SERVICE;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.TRAINING_SERVICE;
import static ua.nure.illiashenko.ilona.constants.ParameterConstants.ID;

@WebServlet("/training/goals")
public class TrainingGoalsServlet extends HttpServlet {

    private TrainingService trainingService;
    private TeamService teamService;
    private ResponseWriter responseWriter;
    private DataValidator dataValidator;

    @Override
    public void init() {
        trainingService = (TrainingService) getServletContext().getAttribute(TRAINING_SERVICE);
        teamService = (TeamService) getServletContext().getAttribute(TEAM_SERVICE);
        responseWriter = (ResponseWriter) getServletContext().getAttribute(RESPONSE_WRITER);
        dataValidator = (DataValidator) getServletContext().getAttribute(DATA_VALIDATOR);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TrainingGoalsData trainingGoalsData = new TrainingGoalsData(request);
        List<String> validationErrors = dataValidator.validate(trainingGoalsData);
        if (!validationErrors.isEmpty()) {
            responseWriter.writeValidationErrors(response, validationErrors);
            return;
        }
        TrainingGoals trainingGoals = new TrainingGoals();
        trainingGoals.setTeamId(Integer.parseInt(trainingGoalsData.getTeamId()));
        trainingGoals.setStartDateTime(Timestamp.valueOf(trainingGoalsData.getStartDateTime()));
        trainingGoals.setEndDateTime(Timestamp.valueOf(trainingGoalsData.getEndDateTime()));
        if (!trainingGoalsData.getPulse().isEmpty()) {
            trainingGoals.setPulse(Integer.parseInt(trainingGoalsData.getPulse()));
        }
        if (!trainingGoalsData.getSpeed().isEmpty()) {
            trainingGoals.setSpeed(Integer.parseInt(trainingGoalsData.getSpeed()));
        }
        trainingService.addTraining(trainingGoals);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String teamIdParameter = Objects.requireNonNull(request.getParameter("teamId"));
        String trainingIdParameter = request.getParameter("trainingId");
        if (!dataValidator.isNumber(teamIdParameter)) {
            response.setStatus(400);
            return;
        }
        int teamId = Integer.parseInt(teamIdParameter);
        if (!teamService.isTeamWithSuchIdExists(teamId)) {
            response.setStatus(404);
            return;
        }
        if (trainingIdParameter == null) {
            List<TrainingGoals> trainingGoalsList = trainingService.getAllTeamTrainings(teamId);
            List<String> trainingGoalsAsString = new ArrayList<>();
            for(TrainingGoals trainingGoals: trainingGoalsList){
                trainingGoalsAsString.add(trainingGoals.toString());
            }
            responseWriter.writeAllTeamTrainingGoals(response, trainingGoalsAsString);
            return;
        }
        if (!dataValidator.isNumber(trainingIdParameter)) {
            response.setStatus(400);
            return;
        }
        int trainingId = Integer.parseInt(trainingIdParameter);
        if (!trainingService.isTrainingWithSuchIdExists(trainingId)) {
            response.setStatus(404);
            return;
        }
        TrainingGoals trainingGoals = trainingService.getTrainingGoals(trainingId).get();
        responseWriter.writeTrainingGoals(response, trainingGoals);
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TrainingGoalsData trainingGoalsData = new TrainingGoalsData(request);
        if (!trainingService.isTrainingWithSuchIdExists(Integer.parseInt(trainingGoalsData.getId()))) {
            response.setStatus(404);
            return;
        }
        List<String> validationErrors = dataValidator.validate(trainingGoalsData);
        if (!validationErrors.isEmpty()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("validationErrors", validationErrors);
            PrintWriter writer = response.getWriter();
            writer.write(jsonObject.toString());
            writer.print(jsonObject);
            return;
        }
        TrainingGoals trainingGoals = new TrainingGoals();
        trainingGoals.setId(Integer.parseInt(trainingGoalsData.getId()));
        trainingGoals.setTeamId(Integer.parseInt(trainingGoalsData.getTeamId()));
        trainingGoals.setStartDateTime(Timestamp.valueOf(trainingGoalsData.getStartDateTime()));
        trainingGoals.setEndDateTime(Timestamp.valueOf(trainingGoalsData.getEndDateTime()));
        if (!trainingGoalsData.getPulse().isEmpty()) {
            trainingGoals.setPulse(Integer.parseInt(trainingGoalsData.getPulse()));
        }
        if (!trainingGoalsData.getSpeed().isEmpty()) {
            trainingGoals.setSpeed(Integer.parseInt(trainingGoalsData.getSpeed()));
        }
        trainingService.updateTrainingGoals(trainingGoals);
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) {
        String trainingGoalsId = Objects.requireNonNull(request.getParameter(ID));
        if (!dataValidator.isNumber(trainingGoalsId)) {
            response.setStatus(400);
            return;
        }
        int id = Integer.parseInt(trainingGoalsId);
        if (!trainingService.isTrainingWithSuchIdExists(id)) {
            response.setStatus(404);
            return;
        }
        trainingService.deleteTraining(id);
    }
}