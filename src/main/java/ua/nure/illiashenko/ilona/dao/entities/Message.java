package ua.nure.illiashenko.ilona.dao.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
public class Message {

    private int id;
    private int userChatId;
    private Timestamp dateTime;
    private String text;
}
