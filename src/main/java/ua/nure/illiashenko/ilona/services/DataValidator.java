package ua.nure.illiashenko.ilona.services;

import ua.nure.illiashenko.ilona.controllers.dto.LogInData;
import ua.nure.illiashenko.ilona.controllers.dto.UserData;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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
    private final Pattern dateTimePattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}");

    private boolean isLogin(String login) {
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

    private boolean isGender(String gender) {
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

    public List<String> validate(UserData userData) {
        List<String> validationErrors = new ArrayList<>();
        if (!isLogin(userData.getLogin())) {
            validationErrors.add("wrongLogin");
        }
        if (!isName(userData.getFirstName())) {
            validationErrors.add("wrongFirstName");
        }
        if (!isName(userData.getLastName())) {
            validationErrors.add("wrongLastName");
        }
        if (!isEmail(userData.getEmail())) {
            validationErrors.add("wrongEmail");
        }
        if (!isPassword(userData.getPassword())) {
            validationErrors.add("wrongPassword");
        }
        if (!userData.getPassword().equals(userData.getRepeatedPassword())) {
            validationErrors.add("wrongRepeatedPassword");
        }
        if(!isRole(userData.getRole())){
            validationErrors.add("wrongRole");
        }
        if(NEW_TEAM.equals(userData.getTeamType())&&userData.getTeamName().length()==0){
            validationErrors.add("wrongTeamName");
        }
        if (!userData.getBirthDate().isEmpty() && !isDate(userData.getBirthDate())) {
            validationErrors.add("wrongBirthDate");
        }
        if (!userData.getHeight().isEmpty() && !isNumber(userData.getHeight())) {
            validationErrors.add("wrongHeight");
        }
        if (!userData.getWeight().isEmpty() && !isNumber(userData.getWeight())) {
            validationErrors.add("wrongWeight");
        }
        if (!userData.getGender().isEmpty() && !isGender(userData.getGender())) {
            validationErrors.add("wrongGender");
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
}
