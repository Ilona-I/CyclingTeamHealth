package ua.nure.illiashenko.ilona.controllers.servlets.feedback;

import ua.nure.illiashenko.ilona.controllers.ResponseWriter;
import ua.nure.illiashenko.ilona.dao.entities.Feedback;
import ua.nure.illiashenko.ilona.services.FeedbackService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import static ua.nure.illiashenko.ilona.constants.ContextConstants.FEEDBACK_SERVICE;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.RESPONSE_WRITER;

@WebServlet("/feedbacks")
public class GetFeedbacksServlet extends HttpServlet {

    private FeedbackService feedbackService;
    private ResponseWriter responseWriter;

    @Override
    public void init() {
        feedbackService = (FeedbackService) getServletContext().getAttribute(FEEDBACK_SERVICE);
        responseWriter = (ResponseWriter) getServletContext().getAttribute(RESPONSE_WRITER);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<String> feedbacks = feedbackService.getFeedbacks();
        responseWriter.writeFeedbacks(response, feedbacks);
    }
}
