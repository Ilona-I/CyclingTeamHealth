package ua.nure.illiashenko.ilona.controllers.servlets.feedback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.illiashenko.ilona.services.DataValidator;
import ua.nure.illiashenko.ilona.services.FeedbackService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ua.nure.illiashenko.ilona.constants.ContextConstants.DATA_VALIDATOR;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.FEEDBACK_SERVICE;

@WebServlet("/feedback")
public class SendFeedbackServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(SendFeedbackServlet.class);
    private FeedbackService feedbackService;
    private DataValidator dataValidator;

    @Override
    public void init() {
        feedbackService = (FeedbackService) getServletContext().getAttribute(FEEDBACK_SERVICE);
        dataValidator = (DataValidator) getServletContext().getAttribute(DATA_VALIDATOR);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response){

    }
}
