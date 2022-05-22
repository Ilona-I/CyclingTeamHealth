package ua.nure.illiashenko.ilona.exceptions.admin;

public class CannotBackUpDatabaseException extends RuntimeException {

    public CannotBackUpDatabaseException(String message) {
        super(message);
    }
}
