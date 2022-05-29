package ua.nure.illiashenko.ilona.controllers.dto;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static ua.nure.illiashenko.ilona.constants.UserConstants.LOGIN;
import static ua.nure.illiashenko.ilona.constants.UserConstants.PASSWORD;

public class LogInData {

    private final String login;
    private final String password;

    public LogInData(HttpServletRequest request) {
        this.login = Objects.requireNonNull(request.getParameter(LOGIN)).trim();
        this.password = Objects.requireNonNull(request.getParameter(PASSWORD)).trim();
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
