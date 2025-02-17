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
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserDAO userDAO;
    private final TransactionManager transactionManager;
    private final MD5Util md5Util;

    public UserService(UserDAO userDAO, TransactionManager transactionManager, MD5Util md5Util) {
        this.userDAO = userDAO;
        this.transactionManager = transactionManager;
        this.md5Util = md5Util;
    }

    public boolean addUser(User user) throws NoSuchAlgorithmException {
        user.setPassword(md5Util.md5(user.getPassword()));
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
                    newUser.setPassword(md5Util.md5(newUser.getPassword()));
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

    public Map<String, Double> getPulseValues(String gender, Date birthDate) {
        Map<String, Double> pulseValues = new HashMap<>();
        int k = 202;
        double r = 0.55;
        if ("female".equals(gender)) {
            k = 216;
            r = 1.09;
        }
        long age = ((new Date(new java.util.Date().getTime()).getTime() - birthDate.getTime()) / 31536000000L);
        pulseValues.put("maxPulse", k - (r * age));
        pulseValues.put("maxTimeForMaxPulse", 5.0);
        double minPulse = 60;
        if (age > 30 && age <= 40) {
            minPulse = 55;
        }
        if (age > 40 && age <= 60) {
            minPulse = 50;
        }
        pulseValues.put("minPulse", minPulse);
        return pulseValues;
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
