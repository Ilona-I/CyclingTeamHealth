package ua.nure.illiashenko.ilona.dao.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
public class TrainingGoals {

    private int id;
    private int teamId;
    private int pulse;
    private int speed;
    private Timestamp startTime;
    private Timestamp endTime;
}
