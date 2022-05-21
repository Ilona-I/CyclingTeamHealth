package ua.nure.illiashenko.ilona.exceptions.user;

public class CannotFindUserException extends RuntimeException {

    public CannotFindUserException(String message) {
        super(message);
    }
}