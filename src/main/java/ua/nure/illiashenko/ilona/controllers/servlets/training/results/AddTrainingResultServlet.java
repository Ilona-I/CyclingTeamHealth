package ua.nure.illiashenko.ilona.controllers.servlets.training.results;

import ua.nure.illiashenko.ilona.controllers.ResponseWriter;
import ua.nure.illiashenko.ilona.controllers.dto.TrainingResultsData;
import ua.nure.illiashenko.ilona.dao.entities.TrainingResults;
import ua.nure.illiashenko.ilona.services.DataValidator;
import ua.nure.illiashenko.ilona.services.TrainingService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static ua.nure.illiashenko.ilona.constants.ContextConstants.DATA_VALIDATOR;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.RESPONSE_WRITER;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.TRAINING_SERVICE;

@WebServlet("/training/result")
public class AddTrainingResultServlet extends HttpServlet {

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
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TrainingResultsData trainingResultsData = new TrainingResultsData(request);
        List<String> validationErrors = dataValidator.validate(trainingResultsData);
        if (!validationErrors.isEmpty()) {
            responseWriter.writeValidationErrors(response, validationErrors);
            return;
        }
        TrainingResults trainingResults = new TrainingResults();
        trainingResults.setTrainingId(Integer.parseInt(trainingResultsData.getTrainingId()));
        trainingResults.setLogin(trainingResultsData.getLogin());
        trainingResults.setPulse(Integer.parseInt(trainingResultsData.getPulse()));
        trainingResults.setSpeed(Integer.parseInt(trainingResultsData.getSpeed()));
        trainingService.addTrainingResults(trainingResults);
    }
}
