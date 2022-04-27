package ua.nure.illiashenko.ilona.controllers.dto;

import lombok.Getter;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static ua.nure.illiashenko.ilona.constants.UserConstants.EMAIL;
import static ua.nure.illiashenko.ilona.constants.UserConstants.FIRST_NAME;
import static ua.nure.illiashenko.ilona.constants.UserConstants.LAST_NAME;
import static ua.nure.illiashenko.ilona.constants.UserConstants.LOGIN;
import static ua.nure.illiashenko.ilona.constants.UserConstants.PASSWORD;
import static ua.nure.illiashenko.ilona.constants.UserConstants.REPEATED_PASSWORD;

@Getter
public class RegistrationData {


    private final String login;
    private final String password;
    private final String repeatedPassword;
    private final String firstName;
    private final String lastName;
    private final String email;

    public RegistrationData(HttpServletRequest request) {
        this.login = Objects.requireNonNull(request.getParameter(LOGIN)).trim();
        this.firstName = Objects.requireNonNull(request.getParameter(FIRST_NAME)).trim();
        this.lastName = Objects.requireNonNull(request.getParameter(LAST_NAME)).trim();
        this.email = Objects.requireNonNull(request.getParameter(EMAIL)).trim();
        this.password = Objects.requireNonNull(request.getParameter(PASSWORD)).trim();
        this.repeatedPassword = Objects.requireNonNull(request.getParameter(REPEATED_PASSWORD)).trim();
    }
}
