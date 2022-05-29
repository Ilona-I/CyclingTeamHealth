package ua.nure.illiashenko.ilona.controllers.dto;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static ua.nure.illiashenko.ilona.constants.ParameterConstants.END_DATE_TIME;
import static ua.nure.illiashenko.ilona.constants.ParameterConstants.ID;
import static ua.nure.illiashenko.ilona.constants.ParameterConstants.PULSE;
import static ua.nure.illiashenko.ilona.constants.ParameterConstants.SPEED;
import static ua.nure.illiashenko.ilona.constants.ParameterConstants.START_DATE_TIME;
import static ua.nure.illiashenko.ilona.constants.ParameterConstants.TEAM_ID;

public class TrainingGoalsData {

    private final String id;
    private final String teamId;
    private final String pulse;
    private final String speed;
    private final String startDateTime;
    private final String endDateTime;

    public TrainingGoalsData(HttpServletRequest request) {
        this.id = request.getParameter(ID);
        this.teamId = Objects.requireNonNull(request.getParameter(TEAM_ID));
        this.pulse = Objects.requireNonNull(request.getParameter(PULSE));
        this.speed = Objects.requireNonNull(request.getParameter(SPEED));
        this.startDateTime = Objects.requireNonNull(request.getParameter(START_DATE_TIME));
        this.endDateTime = Objects.requireNonNull(request.getParameter(END_DATE_TIME));
    }

    public String getId() {
        return id;
    }

    public String getTeamId() {
        return teamId;
    }

    public String getPulse() {
        return pulse;
    }

    public String getSpeed() {
        return speed;
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }
}
