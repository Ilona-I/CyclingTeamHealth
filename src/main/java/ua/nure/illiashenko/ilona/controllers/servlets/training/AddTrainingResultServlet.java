package ua.nure.illiashenko.ilona.controllers.servlets.training;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.illiashenko.ilona.services.DataValidator;
import ua.nure.illiashenko.ilona.services.TrainingService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ua.nure.illiashenko.ilona.constants.ContextConstants.TRAINING_SERVICE;

@WebServlet("/training/result")
public class AddTrainingResultServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(AddTrainingResultServlet.class);
    private TrainingService trainingService;

    @Override
    public void init() {
        trainingService = (TrainingService) getServletContext().getAttribute(TRAINING_SERVICE);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){

    }
}
