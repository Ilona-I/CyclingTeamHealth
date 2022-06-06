package ua.nure.illiashenko.ilona.controllers.servlets.training.results;

import ua.nure.illiashenko.ilona.controllers.ResponseWriter;
import ua.nure.illiashenko.ilona.dao.entities.TrainingResults;
import ua.nure.illiashenko.ilona.services.DataValidator;
import ua.nure.illiashenko.ilona.services.TrainingService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static ua.nure.illiashenko.ilona.constants.ContextConstants.DATA_VALIDATOR;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.RESPONSE_WRITER;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.TRAINING_SERVICE;

@WebServlet("/training/team/results")
public class TrainingTeamResultsServlet extends HttpServlet {

    private TrainingService trainingService;
    private ResponseWriter responseWriter;
    private DataValidator dataValidator;

    @Override
    public void init() {
        trainingService = (TrainingService) getServletContext().getAttribute(TRAINING_SERVICE);
        responseWriter = (ResponseWriter) getServletContext().getAttribute(RESPONSE_WRITER);
        dataValidator = (DataValidator) getServletContext().getAttribute(DATA_VALIDATOR);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String trainingId = Objects.requireNonNull(request.getParameter("id"));
        if (!dataValidator.isNumber(trainingId)) {
            response.setStatus(400);
            return;
        }
        int id = Integer.parseInt(trainingId);
        if (!trainingService.isTrainingWithSuchIdExists(id)) {
            response.setStatus(404);
            return;
        }
        List<TrainingResults> trainingResults = trainingService.getTeamTrainingResults(id);
        List<String> trainingResultsAsString = new ArrayList<>();
        for (TrainingResults tr: trainingResults){
            trainingResultsAsString.add(tr.toString());
        }
        responseWriter.writeTrainingResults(response, trainingResultsAsString);
    }
}