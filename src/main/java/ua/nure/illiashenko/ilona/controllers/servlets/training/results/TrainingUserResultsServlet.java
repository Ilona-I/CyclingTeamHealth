package ua.nure.illiashenko.ilona.controllers.servlets.training.results;

import ua.nure.illiashenko.ilona.controllers.ResponseWriter;
import ua.nure.illiashenko.ilona.dao.entities.TrainingResults;
import ua.nure.illiashenko.ilona.services.DataValidator;
import ua.nure.illiashenko.ilona.services.TrainingService;
import ua.nure.illiashenko.ilona.services.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

import static ua.nure.illiashenko.ilona.constants.ContextConstants.DATA_VALIDATOR;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.RESPONSE_WRITER;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.TRAINING_SERVICE;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.USER_SERVICE;

@WebServlet("/training/user/results")
public class TrainingUserResultsServlet extends HttpServlet {

    private TrainingService trainingService;
    private UserService userService;
    private ResponseWriter responseWriter;
    private DataValidator dataValidator;

    @Override
    public void init() {
        trainingService = (TrainingService) getServletContext().getAttribute(TRAINING_SERVICE);
        userService = (UserService) getServletContext().getAttribute(USER_SERVICE);
        responseWriter = (ResponseWriter) getServletContext().getAttribute(RESPONSE_WRITER);
        dataValidator = (DataValidator) getServletContext().getAttribute(DATA_VALIDATOR);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String trainingId = Objects.requireNonNull(request.getParameter("trainingId"));
        String login = Objects.requireNonNull(request.getParameter("login"));
        if (!dataValidator.isNumber(trainingId) || !dataValidator.isLogin(login)) {
            response.setStatus(400);
            return;
        }
        int id = Integer.parseInt(trainingId);
        if (!trainingService.isTrainingWithSuchIdExists(id) || !userService.isUserWithSuchLoginExists(login)) {
            response.setStatus(404);
            return;
        }
        TrainingResults trainingResults = trainingService.getUserTrainingResults(login, id).get();
        responseWriter.writeUserTrainingResults(response, trainingResults);
    }
}
