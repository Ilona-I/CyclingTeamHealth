package ua.nure.illiashenko.ilona.dao.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserChat {

    private int id;
    private String login;
    private int chatId;
}
