package ua.nure.illiashenko.ilona.controllers.dto;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static ua.nure.illiashenko.ilona.constants.ParameterConstants.PULSE;
import static ua.nure.illiashenko.ilona.constants.ParameterConstants.SPEED;
import static ua.nure.illiashenko.ilona.constants.ParameterConstants.TRAINING_ID;
import static ua.nure.illiashenko.ilona.constants.UserConstants.LOGIN;

public class TrainingResultsData {

    private final String trainingId;
    private final String login;
    private final String pulse;
    private final String speed;

    public TrainingResultsData(HttpServletRequest request) {
        this.trainingId = Objects.requireNonNull(request.getParameter(TRAINING_ID));
        this.login = Objects.requireNonNull(request.getParameter(LOGIN));
        this.pulse = Objects.requireNonNull(request.getParameter(PULSE));
        this.speed = Objects.requireNonNull(request.getParameter(SPEED));
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
