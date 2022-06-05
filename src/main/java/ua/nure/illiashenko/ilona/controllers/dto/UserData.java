package ua.nure.illiashenko.ilona.controllers.dto;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static ua.nure.illiashenko.ilona.constants.UserConstants.BIRTH_DATE;
import static ua.nure.illiashenko.ilona.constants.UserConstants.EMAIL;
import static ua.nure.illiashenko.ilona.constants.UserConstants.FIRST_NAME;
import static ua.nure.illiashenko.ilona.constants.UserConstants.GENDER;
import static ua.nure.illiashenko.ilona.constants.UserConstants.HEIGHT;
import static ua.nure.illiashenko.ilona.constants.UserConstants.LAST_NAME;
import static ua.nure.illiashenko.ilona.constants.UserConstants.LOGIN;
import static ua.nure.illiashenko.ilona.constants.UserConstants.STATUS;
import static ua.nure.illiashenko.ilona.constants.UserConstants.WEIGHT;

public class UserData {

    private final String login;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String birthDate;
    private final String height;
    private final String weight;
    private final String gender;
    private final String status;

    public UserData(HttpServletRequest request) {
        this.login = Objects.requireNonNull(request.getParameter(LOGIN)).trim();
        this.firstName = Objects.requireNonNull(request.getParameter(FIRST_NAME)).trim();
        this.lastName = Objects.requireNonNull(request.getParameter(LAST_NAME)).trim();
        this.email = Objects.requireNonNull(request.getParameter(EMAIL)).trim();
        this.birthDate = Objects.requireNonNull(request.getParameter(BIRTH_DATE)).trim();
        this.height = Objects.requireNonNull(request.getParameter(HEIGHT)).trim();
        this.weight = Objects.requireNonNull(request.getParameter(WEIGHT)).trim();
        this.gender = request.getParameter(GENDER);
        this.status = request.getParameter(STATUS);
    }

    public String getLogin() {
        return login;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getHeight() {
        return height;
    }

    public String getWeight() {
        return weight;
    }

    public String getGender() {
        return gender;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", gender='" + gender + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
