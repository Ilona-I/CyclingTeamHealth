package ua.nure.illiashenko.ilona.controllers.dto;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class TrainingResultsData {

    private final String trainingId;
    private final String login;
    private final String pulse;
    private final String speed;

    public TrainingResultsData(HttpServletRequest request){
        this.trainingId = Objects.requireNonNull(request.getParameter("trainingId"));
        this.login = Objects.requireNonNull(request.getParameter("login"));
        this.pulse = Objects.requireNonNull(request.getParameter("pulse"));
        this.speed = Objects.requireNonNull(request.getParameter("speed"));
    }

    public String getTrainingId() {
        return trainingId;
    }

    public String getLogin() {
        return login;
    }

    public String getPulse() {
        return pulse;
    }

    public String getSpeed() {
        return speed;
    }
}
