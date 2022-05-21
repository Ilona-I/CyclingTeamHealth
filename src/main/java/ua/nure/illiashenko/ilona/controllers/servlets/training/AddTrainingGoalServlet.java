package ua.nure.illiashenko.ilona.controllers.servlets.training;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.illiashenko.ilona.controllers.servlets.team.AddUserToTeamServlet;
import ua.nure.illiashenko.ilona.services.DataValidator;
import ua.nure.illiashenko.ilona.services.TeamService;
import ua.nure.illiashenko.ilona.services.TrainingService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ua.nure.illiashenko.ilona.constants.ContextConstants.DATA_VALIDATOR;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.TEAM_SERVICE;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.TRAINING_SERVICE;

@WebServlet("/training/goal")
public class AddTrainingGoalServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(AddTrainingGoalServlet.class);
    private TrainingService trainingService;
    private DataValidator dataValidator;

    @Override
    public void init() {
        trainingService = (TrainingService) getServletContext().getAttribute(TRAINING_SERVICE);
        dataValidator = (DataValidator) getServletContext().getAttribute(DATA_VALIDATOR);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {

    }
}