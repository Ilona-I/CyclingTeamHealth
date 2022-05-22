package ua.nure.illiashenko.ilona.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.illiashenko.ilona.dao.UserDAO;
import ua.nure.illiashenko.ilona.dao.entities.User;
import ua.nure.illiashenko.ilona.exceptions.CannotDoTransactionException;
import ua.nure.illiashenko.ilona.exceptions.user.CannotAddNewUserException;
import ua.nure.illiashenko.ilona.exceptions.user.CannotDeleteUserException;
import ua.nure.illiashenko.ilona.exceptions.user.CannotFindUserException;
import ua.nure.illiashenko.ilona.exceptions.user.CannotUpdateUserException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.function.Function;

public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserDAO userDAO;
    private final TransactionManager transactionManager;

    public UserService(UserDAO userDAO, TransactionManager transactionManager) {
        this.userDAO = userDAO;
        this.transactionManager = transactionManager;
    }

    public boolean addUser(User user) {
        Function<Connection, Boolean> function = connection -> {
            try {
                userDAO.insert(user, connection);
                return true;
            } catch (SQLException e) {
                throw new CannotAddNewUserException(e.getMessage());
            }
        };
        return doInTransaction(function);
    }

    public boolean updateUser(String login, User newUser) {
        Function<Connection, Boolean> function = connection -> {
            try {
                userDAO.update(login, newUser, connection);
                return true;
            } catch (SQLException e) {
                throw new CannotUpdateUserException(e.getMessage());
            }
        };
        return doInTransaction(function);
    }

    public Optional<User> getUser(String login) {
        Function<Connection, Optional<User>> function = connection -> {
            try {
                return userDAO.get(login, connection);
            } catch (SQLException e) {
                logger.error(e.getMessage());
                throw new CannotFindUserException(e.getMessage());
            }
        };
        return doInTransaction(function);
    }

    public boolean deleteUser(String login) {
        Function<Connection, Boolean> function = connection -> {
            try {
                userDAO.delete(login, connection);
                return true;
            } catch (SQLException e) {
                throw new CannotDeleteUserException(e.getMessage());
            }
        };
        return doInTransaction(function);
    }

    public boolean isUserWithSuchLoginExists(String login) {
        Function<Connection, Optional<User>> function = connection -> {
            try {
                return userDAO.get(login, connection);
            } catch (SQLException e) {
                logger.error(e.getMessage());
                throw new CannotFindUserException(e.getMessage());
            }
        };
        return doInTransaction(function).isPresent();
    }

    private <R> R doInTransaction(Function<Connection, R> function) {
        try {
            return transactionManager.doInTransaction(function);
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new CannotDoTransactionException(e.getMessage());
        }
    }
}
