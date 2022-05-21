package ua.nure.illiashenko.ilona.controllers.servlets.feedback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.illiashenko.ilona.services.FeedbackService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ua.nure.illiashenko.ilona.constants.ContextConstants.FEEDBACK_SERVICE;

@WebServlet("/feedback")
public class DeleteFeedbackServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(DeleteFeedbackServlet.class);
    private FeedbackService feedbackService;

    @Override
    public void init() {
        feedbackService = (FeedbackService) getServletContext().getAttribute(FEEDBACK_SERVICE);
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response){

    }
}