package ua.nure.illiashenko.ilona.controllers.dto;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class TeamData {

    private String id;
    private String name;

    public TeamData(HttpServletRequest request) {
        this.id = Objects.requireNonNull(request.getParameter("teamId")).trim();
        this.name = Objects.requireNonNull(request.getParameter("teamName")).trim();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
