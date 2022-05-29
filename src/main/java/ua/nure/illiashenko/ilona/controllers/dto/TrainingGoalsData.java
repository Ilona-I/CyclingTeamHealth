package ua.nure.illiashenko.ilona.controllers.dto;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class TrainingGoalsData {

    private final String id;
    private final String teamId;
    private final String pulse;
    private final String speed;
    private final String startDateTime;
    private final String endDateTime;

    public TrainingGoalsData(HttpServletRequest request){
        this.id = request.getParameter("id");
        this.teamId = Objects.requireNonNull(request.getParameter("teamId"));
        this.pulse = Objects.requireNonNull(request.getParameter("pulse"));
        this.speed = Objects.requireNonNull(request.getParameter("speed"));
        this.startDateTime = Objects.requireNonNull(request.getParameter("startDateTime"));
        this.endDateTime = Objects.requireNonNull(request.getParameter("endDateTime"));
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
