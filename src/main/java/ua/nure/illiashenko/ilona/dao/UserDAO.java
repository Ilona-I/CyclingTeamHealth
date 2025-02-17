package ua.nure.illiashenko.ilona.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.illiashenko.ilona.dao.entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ua.nure.illiashenko.ilona.constants.SQLQuery.DELETE_USER;
import static ua.nure.illiashenko.ilona.constants.SQLQuery.GET_ALL_USERS;
import static ua.nure.illiashenko.ilona.constants.SQLQuery.GET_TEAM_MEMBERS;
import static ua.nure.illiashenko.ilona.constants.SQLQuery.GET_USER_BY_LOGIN;
import static ua.nure.illiashenko.ilona.constants.SQLQuery.INSERT_USER;
import static ua.nure.illiashenko.ilona.constants.SQLQuery.UPDATE_USER;

public class UserDAO implements DAO<User, String> {

    private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);

    @Override
    public User insert(User user, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)) {
            setUserData(user, preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException();
        }
        return user;
    }

    @Override
    public Optional<User> get(String login, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(getUser(resultSet));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException();
        }
        return Optional.empty();
    }

    @Override
    public boolean update(String login, User user, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {
            setUserData(user, preparedStatement);
            preparedStatement.setString(13, login);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException();
        }
    }

    private void setUserData(User user, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, user.getLogin());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getFirstName());
        preparedStatement.setString(4, user.getLastName());
        preparedStatement.setString(5, user.getEmail());
        preparedStatement.setString(6, user.getRole());
        preparedStatement.setInt(7, user.getTeamId());
        preparedStatement.setDate(8, user.getBirthDate());
        preparedStatement.setDouble(9, user.getHeight());
        preparedStatement.setDouble(10, user.getWeight());
        preparedStatement.setString(11, user.getGender());
        preparedStatement.setString(12, user.getStatus());
    }

    @Override
    public boolean delete(String login, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)) {
            preparedStatement.setString(1, login);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException();
        }
    }

    public List<String> getTeamMembers(int teamId, Connection connection) throws SQLException {
        List<String> teamMembers = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_TEAM_MEMBERS)) {
            preparedStatement.setInt(1, teamId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                teamMembers.add(getUser(resultSet).toString());
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException();
        }
        return teamMembers;
    }

    private User getUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setLogin(resultSet.getString(1));
        user.setPassword(resultSet.getString(2));
        user.setFirstName(resultSet.getString(3));
        user.setLastName(resultSet.getString(4));
        user.setEmail(resultSet.getString(5));
        user.setRole(resultSet.getString(6));
        user.setTeamId(resultSet.getInt(7));
        user.setBirthDate(resultSet.getDate(8));
        user.setHeight(resultSet.getDouble(9));
        user.setWeight(resultSet.getDouble(10));
        user.setGender(resultSet.getString(11));
        user.setStatus(resultSet.getString(12));
        return user;
    }

    public List<String> getUsers(Connection connection) throws SQLException {
        List<String> teamMembers = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                teamMembers.add(getUser(resultSet).toString());
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException();
        }
        return teamMembers;
    }
}
