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
import static ua.nure.illiashenko.ilona.constants.UserConstants.PASSWORD;
import static ua.nure.illiashenko.ilona.constants.UserConstants.REPEATED_PASSWORD;
import static ua.nure.illiashenko.ilona.constants.UserConstants.ROLE;
import static ua.nure.illiashenko.ilona.constants.UserConstants.TEAM_ID;
import static ua.nure.illiashenko.ilona.constants.UserConstants.TEAM_NAME;
import static ua.nure.illiashenko.ilona.constants.UserConstants.TEAM_TYPE;
import static ua.nure.illiashenko.ilona.constants.UserConstants.WEIGHT;

public class RegistrationData {

    private final String login;
    private final String password;
    private final String repeatedPassword;
    private final String firstName;
    private final String lastName;
    private final String role;
    private final String teamType;
    private final String teamId;
    private final String teamName;
    private final String email;
    private final String birthDate;
    private final String height;
    private final String weight;
    private final String gender;

    public RegistrationData(HttpServletRequest request) {
        this.login = Objects.requireNonNull(request.getParameter(LOGIN)).trim();
        this.firstName = Objects.requireNonNull(request.getParameter(FIRST_NAME)).trim();
        this.lastName = Objects.requireNonNull(request.getParameter(LAST_NAME)).trim();
        this.email = Objects.requireNonNull(request.getParameter(EMAIL)).trim();
        this.role = Objects.requireNonNull(request.getParameter(ROLE)).trim();
        this.teamType = Objects.requireNonNull(request.getParameter(TEAM_TYPE)).trim();
        this.teamId = Objects.requireNonNull(request.getParameter(TEAM_ID)).trim();
        this.teamName = Objects.requireNonNull(request.getParameter(TEAM_NAME)).trim();
        this.password = Objects.requireNonNull(request.getParameter(PASSWORD)).trim();
        this.repeatedPassword = Objects.requireNonNull(request.getParameter(REPEATED_PASSWORD)).trim();
        this.birthDate = Objects.requireNonNull(request.getParameter(BIRTH_DATE)).trim();
        this.height = Objects.requireNonNull(request.getParameter(HEIGHT)).trim();
        this.weight = Objects.requireNonNull(request.getParameter(WEIGHT)).trim();
        this.gender = Objects.requireNonNull(request.getParameter(GENDER)).trim();
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getRepeatedPassword() {
        return repeatedPassword;
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

    public String getRole() {
        return role;
    }

    public String getTeamId() {
        return teamId;
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

    public String getTeamType() {
        return teamType;
    }

    public String getTeamName() {
        return teamName;
    }

    @Override
    public String toString() {
        return "RegistrationData{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", repeatedPassword='" + repeatedPassword + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role='" + role + '\'' +
                ", teamType='" + teamType + '\'' +
                ", teamId='" + teamId + '\'' +
                ", teamName='" + teamName + '\'' +
                ", email='" + email + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
