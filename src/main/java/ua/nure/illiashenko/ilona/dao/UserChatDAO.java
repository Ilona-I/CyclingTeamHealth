package ua.nure.illiashenko.ilona.dao;

import com.mysql.cj.jdbc.exceptions.OperationNotSupportedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.illiashenko.ilona.dao.entities.UserChat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static ua.nure.illiashenko.ilona.constants.SQLQuery.DELETE_USER_CHAT;
import static ua.nure.illiashenko.ilona.constants.SQLQuery.GET_CHAT;
import static ua.nure.illiashenko.ilona.constants.SQLQuery.GET_USERS_CHAT_ID;
import static ua.nure.illiashenko.ilona.constants.SQLQuery.GET_USER_CHATS;
import static ua.nure.illiashenko.ilona.constants.SQLQuery.INSERT_USER_CHAT;

public class UserChatDAO implements DAO<UserChat, Integer> {

    private static final Logger logger = LoggerFactory.getLogger(UserChatDAO.class);

    @Override
    public UserChat insert(UserChat userChat, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_CHAT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, userChat.getChatId());
            preparedStatement.setString(2, userChat.getLogin());
            preparedStatement.executeUpdate();
            ResultSet keys = preparedStatement.getGeneratedKeys();
            keys.next();
            userChat.setId(keys.getInt(1));
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException();
        }
        return userChat;
    }

    @Override
    public Optional<UserChat> get(Integer key, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_CHAT)) {
            preparedStatement.setInt(1, key);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                UserChat userChat = new UserChat();
                userChat.setId(resultSet.getInt(1));
                userChat.setChatId(resultSet.getInt(2));
                userChat.setLogin(resultSet.getString(3));
                return Optional.of(userChat);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException();
        }
        return Optional.empty();
    }

    @Override
    public boolean update(Integer key, UserChat newEntity, Connection connection) throws SQLException {
        throw new OperationNotSupportedException();
    }

    @Override
    public boolean delete(Integer key, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_CHAT)) {
            preparedStatement.setInt(1, key);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException();
        }
    }

    public Map<Integer, String> getUserChats(String login, Connection connection) throws SQLException {
        Map<Integer, String> userChats = new HashMap<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_CHATS)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userChats.put(resultSet.getInt(1), resultSet.getString(2));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException();
        }
        return userChats;
    }

    public Optional<Integer> getUsersChatId(String sender, String receiver, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_USERS_CHAT_ID)) {
            preparedStatement.setString(1, sender);
            preparedStatement.setString(2, receiver);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException();
        }
        return Optional.empty();
    }
}
