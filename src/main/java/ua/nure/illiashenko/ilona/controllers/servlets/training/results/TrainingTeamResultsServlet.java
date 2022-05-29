package ua.nure.illiashenko.ilona.controllers.servlets.training.results;

import org.json.JSONObject;
import ua.nure.illiashenko.ilona.services.DataValidator;
import ua.nure.illiashenko.ilona.services.TrainingService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

import static ua.nure.illiashenko.ilona.constants.ContextConstants.DATA_VALIDATOR;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.TRAINING_SERVICE;

@WebServlet("/training/team/results")
public class TrainingTeamResultsServlet extends HttpServlet {

    private TrainingService trainingService;
    private DataValidator dataValidator;

    @Override
    public void init() {
        trainingService = (TrainingService) getServletContext().getAttribute(TRAINING_SERVICE);
        dataValidator = (DataValidator) getServletContext().getAttribute(DATA_VALIDATOR);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String trainingId = Objects.requireNonNull(request.getParameter("trainingId"));
        if (!dataValidator.isNumber(trainingId)) {
            response.setStatus(400);
            return;
        }
        int id = Integer.parseInt(trainingId);
        if (!trainingService.isTrainingWithSuchIdExists(id)) {
            response.setStatus(404);
            return;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("teamTrainingResults", trainingService.getTeamTrainingResults(id));
        PrintWriter writer = response.getWriter();
        writer.write(jsonObject.toString());
        writer.print(jsonObject);
    }
}