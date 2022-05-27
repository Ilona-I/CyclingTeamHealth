package ua.nure.illiashenko.ilona.exceptions.chat;

public class CannotSendMessageException extends RuntimeException {

    public CannotSendMessageException(String message) {
        super(message);
    }
}
