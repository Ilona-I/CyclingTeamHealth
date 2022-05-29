package ua.nure.illiashenko.ilona.controllers.servlets.training.results;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.illiashenko.ilona.controllers.dto.TrainingResultsData;
import ua.nure.illiashenko.ilona.dao.entities.TrainingResults;
import ua.nure.illiashenko.ilona.services.DataValidator;
import ua.nure.illiashenko.ilona.services.TrainingService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static ua.nure.illiashenko.ilona.constants.ContextConstants.DATA_VALIDATOR;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.TRAINING_SERVICE;

@WebServlet("/training/result")
public class AddTrainingResultServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(AddTrainingResultServlet.class);
    private TrainingService trainingService;
    private DataValidator dataValidator;

    @Override
    public void init() {
        trainingService = (TrainingService) getServletContext().getAttribute(TRAINING_SERVICE);
        dataValidator = (DataValidator) getServletContext().getAttribute(DATA_VALIDATOR);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TrainingResultsData trainingResultsData = new TrainingResultsData(request);
        List<String> validationErrors = dataValidator.validate(trainingResultsData);
        if (!validationErrors.isEmpty()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("validationErrors", validationErrors);
            PrintWriter writer = response.getWriter();
            writer.write(jsonObject.toString());
            writer.print(jsonObject);
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
