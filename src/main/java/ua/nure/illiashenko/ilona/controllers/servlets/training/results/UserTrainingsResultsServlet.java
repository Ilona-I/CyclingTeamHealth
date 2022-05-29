package ua.nure.illiashenko.ilona.controllers.servlets.training.results;

import org.json.JSONObject;
import ua.nure.illiashenko.ilona.services.DataValidator;
import ua.nure.illiashenko.ilona.services.TrainingService;
import ua.nure.illiashenko.ilona.services.UserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

import static ua.nure.illiashenko.ilona.constants.ContextConstants.DATA_VALIDATOR;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.TRAINING_SERVICE;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.USER_SERVICE;

@WebServlet("/trainings/user/results")
public class UserTrainingsResultsServlet extends HttpServlet {

    private TrainingService trainingService;
    private UserService userService;
    private DataValidator dataValidator;

    @Override
    public void init() {
        trainingService = (TrainingService) getServletContext().getAttribute(TRAINING_SERVICE);
        userService = (UserService) getServletContext().getAttribute(USER_SERVICE);
        dataValidator = (DataValidator) getServletContext().getAttribute(DATA_VALIDATOR);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = Objects.requireNonNull(request.getParameter("login"));
        if (!dataValidator.isLogin(login)) {
            response.setStatus(400);
            return;
        }
        if (!userService.isUserWithSuchLoginExists(login)) {
            response.setStatus(404);
            return;
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userTrainingsResults", trainingService.getUserTrainingsResults(login));
        PrintWriter writer = response.getWriter();
        writer.write(jsonObject.toString());
        writer.print(jsonObject);
    }
}
