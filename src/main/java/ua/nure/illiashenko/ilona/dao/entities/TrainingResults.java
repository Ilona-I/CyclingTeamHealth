package ua.nure.illiashenko.ilona.dao.entities;

import java.io.Serializable;

public class TrainingResults  implements Serializable {

    private int id;
    private int trainingId;
    private String login;
    private int pulse;
    private int speed;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(int trainingId) {
        this.trainingId = trainingId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getPulse() {
        return pulse;
    }

    public void setPulse(int pulse) {
        this.pulse = pulse;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":\"" + id +
                "\", \"trainingId\":\"" + trainingId +
                "\", \"login\":\"" + login +
                "\", \"pulse\":\"" + pulse +
                "\", \"speed\":\"" + speed +
                "\"}";
    }
}
