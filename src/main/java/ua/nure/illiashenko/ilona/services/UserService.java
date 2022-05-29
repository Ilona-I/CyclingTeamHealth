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
import ua.nure.illiashenko.ilona.utils.MD5Util;

import java.security.NoSuchAlgorithmException;
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

    public boolean addUser(User user) throws NoSuchAlgorithmException {
        user.setPassword(MD5Util.md5(user.getPassword()));
        Function<Connection, Boolean> function = connection -> {
            try {
                userDAO.insert(user, connection);
                return true;
            } catch (SQLException e) {
                logger.error(e.getMessage());
                throw new CannotAddNewUserException(e.getMessage());
            }
        };
        return doInTransaction(function);
    }

    public boolean updateUser(String login, User newUser) {
        Function<Connection, Boolean> function = connection -> {
            try {
                User user = userDAO.get(login, connection).get();
                if (!user.getPassword().equals(newUser.getPassword())) {
                    newUser.setPassword(MD5Util.md5(newUser.getPassword()));
                }
                userDAO.update(login, newUser, connection);
                return true;
            } catch (SQLException | NoSuchAlgorithmException e) {
                logger.error(e.getMessage());
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
                logger.error(e.getMessage());
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
