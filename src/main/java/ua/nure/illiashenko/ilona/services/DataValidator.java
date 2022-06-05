package ua.nure.illiashenko.ilona.services;

import ua.nure.illiashenko.ilona.controllers.dto.FeedbackData;
import ua.nure.illiashenko.ilona.controllers.dto.LogInData;
import ua.nure.illiashenko.ilona.controllers.dto.MessageData;
import ua.nure.illiashenko.ilona.controllers.dto.RegistrationData;
import ua.nure.illiashenko.ilona.controllers.dto.TeamData;
import ua.nure.illiashenko.ilona.controllers.dto.TrainingGoalsData;
import ua.nure.illiashenko.ilona.controllers.dto.TrainingResultsData;
import ua.nure.illiashenko.ilona.controllers.dto.UserData;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static ua.nure.illiashenko.ilona.constants.ChatType.PRIVATE;
import static ua.nure.illiashenko.ilona.constants.ChatType.PUBLIC;
import static ua.nure.illiashenko.ilona.constants.ChatType.RECOMMENDATIONS;
import static ua.nure.illiashenko.ilona.constants.StatusType.ACTIVE;
import static ua.nure.illiashenko.ilona.constants.StatusType.BLOCKED;
import static ua.nure.illiashenko.ilona.constants.TeamType.NEW_TEAM;
import static ua.nure.illiashenko.ilona.constants.UserRole.CYCLIST;
import static ua.nure.illiashenko.ilona.constants.UserRole.DOCTOR;
import static ua.nure.illiashenko.ilona.constants.UserRole.SOIGNEUR;
import static ua.nure.illiashenko.ilona.constants.UserRole.TRAINER;

public class DataValidator {

    private final Pattern doubleNumberPattern = Pattern.compile("\\d+(\\.\\d*)?");
    private final Pattern numberPattern = Pattern.compile("\\d+");
    private final Pattern namePattern = Pattern.compile("^[`'А-Яа-яA-Za-zІіЇїЁёЪъЫы]+$");
    private final Pattern emailPattern = Pattern.compile("^[_A-Za-z0-9]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,8})$");
    private final Pattern passwordPattern = Pattern.compile("^(?=.*[a-zA-Z].)(?=.*[0-9]).{5,}$");
    private final Pattern loginPattern = Pattern.compile("^[_A-Za-z0-9]{4,20}$");
    private final Pattern genderPattern = Pattern.compile("^male|female$");
    private final Pattern datePattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
    private final Pattern dateTimePattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.?\\d?");

    public boolean isLogin(String login) {
        if (login == null) {
            return false;
        }
        return loginPattern.matcher(login).matches();
    }

    private boolean isName(String name) {
        if (name == null) {
            return false;
        }
        return namePattern.matcher(name).matches();
    }

    private boolean isEmail(String email) {
        if (email == null) {
            return false;
        }
        return emailPattern.matcher(email).matches();
    }

    private boolean isPassword(String password) {
        if (password == null) {
            return false;
        }
        return passwordPattern.matcher(password).matches();
    }

    public boolean isDoubleNumber(String number) {
        if (number == null) {
            return false;
        }
        return doubleNumberPattern.matcher(number).matches();
    }

    public boolean isNumber(String number) {
        if (number == null) {
            return false;
        }
        return numberPattern.matcher(number).matches();
    }

    public boolean isDate(String date) {
        if (date == null) {
            return false;
        }
        return datePattern.matcher(date).matches();
    }

    private boolean isDateTime(String dateTime) {
        if (dateTime == null) {
            return false;
        }
        return dateTimePattern.matcher(dateTime).matches();
    }

    public boolean isGender(String gender) {
        if (gender == null) {
            return false;
        }
        return genderPattern.matcher(gender).matches();
    }

    private boolean isRole(String role) {
        if (role == null) {
            return false;
        }
        return TRAINER.equals(role) || CYCLIST.equals(role) || DOCTOR.equals(role) || SOIGNEUR.equals(role);
    }

    private boolean isStatus(String status) {
        if (status == null) {
            return false;
        }
        return ACTIVE.equals(status) || BLOCKED.equals(status);
    }

    public boolean isChatType(String chatType) {
        if (chatType == null) {
            return false;
        }
        return PRIVATE.equals(chatType) || PUBLIC.equals(chatType) || RECOMMENDATIONS.equals(chatType);
    }

    public List<String> validate(RegistrationData registrationData) {
        List<String> validationErrors = validate(registrationData.getLogin(), registrationData.getFirstName(), registrationData.getLastName(), registrationData.getEmail(), registrationData.getBirthDate(), registrationData.getHeight(), registrationData.getWeight(), registrationData.getGender());
        if (!isPassword(registrationData.getPassword())) {
            validationErrors.add("wrongPassword");
        }
        if (!registrationData.getPassword().equals(registrationData.getRepeatedPassword())) {
            validationErrors.add("wrongRepeatedPassword");
        }
        if (!isRole(registrationData.getRole())) {
            validationErrors.add("wrongRole");
        }
        if (NEW_TEAM.equals(registrationData.getTeamType()) && registrationData.getTeamName().length() == 0) {
            validationErrors.add("wrongTeamName");
        }
        return validationErrors;
    }

    public List<String> validate(LogInData logInData) {
        List<String> validationErrors = new ArrayList<>();
        if (!isLogin(logInData.getLogin())) {
            validationErrors.add("wrongLogin");
        }
        if (!isPassword(logInData.getPassword())) {
            validationErrors.add("wrongPassword");
        }
        return validationErrors;
    }

    public List<String> validate(FeedbackData feedbackData) {
        List<String> validationErrors = new ArrayList<>();
        if (!feedbackData.getId().isEmpty() && !isNumber(feedbackData.getId())) {
            validationErrors.add("wrongId");
        }
        if (!isLogin(feedbackData.getLogin())) {
            validationErrors.add("wrongLogin");
        }
        if (!feedbackData.getDateTime().isEmpty() && !isDateTime(feedbackData.getDateTime())) {
            validationErrors.add("wrongDateTime");
        }
        if (!isNumber(feedbackData.getRating())) {
            validationErrors.add("wrongRating");
        }
        if (!feedbackData.getStatus().isEmpty() && !isStatus(feedbackData.getStatus())) {
            validationErrors.add("wrongStatus");
        }
        return validationErrors;
    }

    public List<String> validate(TeamData teamData) {
        List<String> validationErrors = new ArrayList<>();
        if (!isNumber(teamData.getId())) {
            validationErrors.add("wrongTeamId");
        }
        if (teamData.getName().isEmpty()) {
            validationErrors.add("wrongTeamName");
        }
        return validationErrors;
    }

    public List<String> validate(UserData userData) {
        return validate(userData.getLogin(), userData.getFirstName(), userData.getLastName(), userData.getEmail(), userData.getBirthDate(), userData.getHeight(), userData.getWeight(), userData.getGender());
    }

    private List<String> validate(String login, String firstName, String lastName, String email, String birthDate, String height, String weight, String gender) {
        List<String> validationErrors = new ArrayList<>();
        if (!isLogin(login)) {
            validationErrors.add("wrongLogin");
        }
        if (!isName(firstName)) {
            validationErrors.add("wrongFirstName");
        }
        if (!isName(lastName)) {
            validationErrors.add("wrongLastName");
        }
        if (!isEmail(email)) {
            validationErrors.add("wrongEmail");
        }
        if (!birthDate.isEmpty() && !isDate(birthDate)) {
            validationErrors.add("wrongBirthDate");
        }
        if (!height.isEmpty() && !isDoubleNumber(height)) {
            validationErrors.add("wrongHeight");
        }
        if (!weight.isEmpty() && !isDoubleNumber(weight)) {
            validationErrors.add("wrongWeight");
        }
        if (!gender.isEmpty() && !isGender(gender)) {
            validationErrors.add("wrongGender");
        }
        return validationErrors;
    }

    public List<String> validate(TrainingGoalsData trainingGoalsData) {
        List<String> validationErrors = new ArrayList<>();
        if (!trainingGoalsData.getId().isEmpty() && !isNumber(trainingGoalsData.getId())) {
            validationErrors.add("wrongId");
        }
        if (!isNumber(trainingGoalsData.getTeamId())) {
            validationErrors.add("wrongTeamId");
        }
        if (!trainingGoalsData.getPulse().isEmpty() && !isNumber(trainingGoalsData.getPulse())) {
            validationErrors.add("wrongPulse");
        }
        if (!trainingGoalsData.getSpeed().isEmpty() && !isNumber(trainingGoalsData.getSpeed())) {
            validationErrors.add("wrongSpeed");
        }
        if (!isDateTime(trainingGoalsData.getStartDateTime())) {
            validationErrors.add("wrongStartDateTime");
        }
        if (!isDateTime(trainingGoalsData.getEndDateTime())) {
            validationErrors.add("wrongEndDateTime");
        }
        return validationErrors;
    }

    public List<String> validate(TrainingResultsData trainingResultsData) {
        List<String> validationErrors = new ArrayList<>();
        if (!isNumber(trainingResultsData.getTrainingId())) {
            validationErrors.add("wrongTrainingId");
        }
        if (!isLogin(trainingResultsData.getLogin())) {
            validationErrors.add("wrongLogin");
        }
        if (!isNumber(trainingResultsData.getPulse())) {
            validationErrors.add("wrongPulse");
        }
        if (!isNumber(trainingResultsData.getSpeed())) {
            validationErrors.add("wrongSpeed");
        }
        return validationErrors;
    }

    public List<String> validate(MessageData messageData) {
        List<String> validationErrors = new ArrayList<>();
        if (!isNumber(messageData.getChatId())) {
            validationErrors.add("wrongChatId");
        }
        if (!isLogin(messageData.getSender())) {
            validationErrors.add("wrongLogin");
        }
        if (messageData.getText().isEmpty()) {
            validationErrors.add("wrongText");
        }
        return validationErrors;
    }
}
