package ua.nure.illiashenko.ilona.exceptions.user;

public class CannotDeleteUserException extends RuntimeException {

    public CannotDeleteUserException(String message) {
        super(message);
    }
}
