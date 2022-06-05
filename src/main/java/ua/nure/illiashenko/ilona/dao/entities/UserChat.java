package ua.nure.illiashenko.ilona.dao.entities;

import java.io.Serializable;

public class UserChat implements Serializable {

    private int id;
    private int chatId;
    private String login;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "{\"id\":\"" + id +
                "\", \"chatId\":\"" + chatId +
                "\", \"login\":\"" + login +
                "\"}";
    }
}
