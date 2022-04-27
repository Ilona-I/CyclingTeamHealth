package ua.nure.illiashenko.ilona.dao.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Builder
public class User {

    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Date birthDate;
    private double height;
    private double weight;
    private String gender;
}
