package ua.nure.illiashenko.ilona.controllers.dto;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static ua.nure.illiashenko.ilona.constants.UserConstants.LOGIN;
import static ua.nure.illiashenko.ilona.constants.UserConstants.STATUS;

public class FeedbackData {

    private final String id;
    private final String login;
    private final String dateTime;
    private final String rating;
    private final String text;
    private final String status;

    public FeedbackData(HttpServletRequest request) {
        this.id = request.getParameter("id");
        this.login = Objects.requireNonNull(request.getParameter(LOGIN));
        this.dateTime = request.getParameter("dateTime");
        this.rating = Objects.requireNonNull(request.getParameter("rating"));
        this.text = Objects.requireNonNull(request.getParameter("text"));
        this.status = request.getParameter(STATUS);
    }

    public String getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getRating() {
        return rating;
    }

    public String getText() {
        return text;
    }

    public String getStatus() {
        return status;
    }
}
