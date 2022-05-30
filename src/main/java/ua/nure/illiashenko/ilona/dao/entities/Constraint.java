package ua.nure.illiashenko.ilona.dao.entities;

import java.util.List;
import java.util.regex.Pattern;

public class Constraint {

    private final Pattern urlPattern;
    private final List<String> roles;

    public Constraint(Pattern urlPattern, List<String> roles) {
        this.urlPattern = urlPattern;
        this.roles = roles;
    }

    public Pattern getUrlPattern() {
        return urlPattern;
    }

    public List<String> getRoles() {
        return roles;
    }
}
