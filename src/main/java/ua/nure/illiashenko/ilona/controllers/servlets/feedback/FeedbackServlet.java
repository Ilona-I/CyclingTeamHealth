package ua.nure.illiashenko.ilona.controllers.servlets.feedback;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.illiashenko.ilona.controllers.dto.FeedbackData;
import ua.nure.illiashenko.ilona.dao.entities.Feedback;
import ua.nure.illiashenko.ilona.services.DataValidator;
import ua.nure.illiashenko.ilona.services.FeedbackService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static ua.nure.illiashenko.ilona.constants.ContextConstants.DATA_VALIDATOR;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.FEEDBACK_SERVICE;
import static ua.nure.illiashenko.ilona.constants.StatusType.ACTIVE;

@WebServlet("/feedback")
public class FeedbackServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(FeedbackServlet.class);
    private FeedbackService feedbackService;
    private DataValidator dataValidator;

    @Override
    public void init() {
        feedbackService = (FeedbackService) getServletContext().getAttribute(FEEDBACK_SERVICE);
        dataValidator = (DataValidator) getServletContext().getAttribute(DATA_VALIDATOR);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        FeedbackData feedbackData = new FeedbackData(request);
        List<String> validationErrors = dataValidator.validate(feedbackData);
        if (!validationErrors.isEmpty()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("validationErrors", validationErrors);
            PrintWriter writer = response.getWriter();
            writer.write(jsonObject.toString());
            writer.print(jsonObject);
        } else {
            Feedback feedback = new Feedback();
            feedback.setLogin(feedbackData.getLogin());
            feedback.setDateTime(new Timestamp(new Date().getTime()));
            feedback.setRating(Integer.parseInt(feedbackData.getRating()));
            feedback.setText(feedbackData.getText());
            feedback.setStatus(ACTIVE);
            feedbackService.createFeedback(feedback);
        }
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        FeedbackData feedbackData = new FeedbackData(request);
        List<String> validationErrors = dataValidator.validate(feedbackData);
        if (!validationErrors.isEmpty()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("validationErrors", validationErrors);
            PrintWriter writer = response.getWriter();
            writer.write(jsonObject.toString());
            writer.print(jsonObject);
        } else {
            Feedback feedback = new Feedback();
            feedback.setId(Integer.parseInt(feedbackData.getId()));
            feedback.setLogin(feedbackData.getLogin());
            feedback.setDateTime(Timestamp.valueOf(feedbackData.getDateTime()));
            feedback.setRating(Integer.parseInt(feedbackData.getRating()));
            feedback.setText(feedbackData.getText());
            feedback.setStatus(feedbackData.getStatus());
            if (feedbackService.createFeedback(feedback)) {
                response.setStatus(200);
            }
        }
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) {
        String feedbackId = Objects.requireNonNull(request.getParameter("feedbackId"));
        if (!dataValidator.isNumber(feedbackId)) {
            response.setStatus(400);
        } else {
            feedbackService.deleteFeedback(Integer.parseInt(feedbackId));
        }
    }
}