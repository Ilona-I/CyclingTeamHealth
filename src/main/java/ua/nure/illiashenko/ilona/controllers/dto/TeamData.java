package ua.nure.illiashenko.ilona.controllers.dto;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static ua.nure.illiashenko.ilona.constants.ParameterConstants.ID;
import static ua.nure.illiashenko.ilona.constants.ParameterConstants.NAME;

public class TeamData {

    private final String id;
    private final String name;

    public TeamData(HttpServletRequest request) {
        this.id = Objects.requireNonNull(request.getParameter(ID)).trim();
        this.name = Objects.requireNonNull(request.getParameter(NAME)).trim();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
