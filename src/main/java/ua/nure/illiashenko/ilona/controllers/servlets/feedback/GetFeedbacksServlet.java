package ua.nure.illiashenko.ilona.controllers.servlets.feedback;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.illiashenko.ilona.dao.entities.Feedback;
import ua.nure.illiashenko.ilona.services.FeedbackService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static ua.nure.illiashenko.ilona.constants.ContextConstants.FEEDBACK_SERVICE;

@WebServlet("/feedbacks")
public class GetFeedbacksServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(GetFeedbacksServlet.class);
    private FeedbackService feedbackService;

    @Override
    public void init() {
        feedbackService = (FeedbackService) getServletContext().getAttribute(FEEDBACK_SERVICE);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Feedback> feedbacks = feedbackService.getFeedbacks();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("feedbacks", feedbacks);
        PrintWriter writer = response.getWriter();
        writer.write(jsonObject.toString());
        writer.print(jsonObject);
    }
}
