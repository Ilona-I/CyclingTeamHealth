package ua.nure.illiashenko.ilona.controllers.servlets.feedback;

import org.json.JSONObject;
import ua.nure.illiashenko.ilona.controllers.ResponseWriter;
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
import java.util.List;

import static ua.nure.illiashenko.ilona.constants.ContextConstants.DATA_VALIDATOR;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.FEEDBACK_SERVICE;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.RESPONSE_WRITER;

@WebServlet("/feedback/update")
public class UpdateFeedbackServlet extends HttpServlet {

    private FeedbackService feedbackService;
    private DataValidator dataValidator;
    private ResponseWriter responseWriter;

    @Override
    public void init() {
        feedbackService = (FeedbackService) getServletContext().getAttribute(FEEDBACK_SERVICE);
        responseWriter = (ResponseWriter) getServletContext().getAttribute(RESPONSE_WRITER);
        dataValidator = (DataValidator) getServletContext().getAttribute(DATA_VALIDATOR);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        FeedbackData feedbackData = new FeedbackData(request);
        System.out.println(feedbackData);
        List<String> validationErrors = dataValidator.validate(feedbackData);
        System.out.println(validationErrors);
        if (!validationErrors.isEmpty()) {
            response.setStatus(400);
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
            System.out.println(feedback);
            if (feedbackService.updateFeedback(feedback)) {
                response.setStatus(200);
            }
        }
    }
}
