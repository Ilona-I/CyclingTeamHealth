package ua.nure.illiashenko.ilona.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.illiashenko.ilona.dao.entities.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ua.nure.illiashenko.ilona.constants.SQLQuery.DELETE_MESSAGE;
import static ua.nure.illiashenko.ilona.constants.SQLQuery.GET_MESSAGE;
import static ua.nure.illiashenko.ilona.constants.SQLQuery.GET_MESSAGES_BY_CHAT_ID;
import static ua.nure.illiashenko.ilona.constants.SQLQuery.INSERT_MESSAGE;
import static ua.nure.illiashenko.ilona.constants.SQLQuery.UPDATE_MESSAGE;

public class MessageDAO implements DAO <Message, Integer> {

    private static final Logger logger = LoggerFactory.getLogger(MessageDAO.class);

    @Override
    public Message insert(Message message, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MESSAGE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, message.getChatId());
            preparedStatement.setString(2, message.getSender());
            preparedStatement.setString(3, message.getText());
            preparedStatement.setTimestamp(4, message.getDateTime());
            preparedStatement.executeUpdate();
            ResultSet keys=preparedStatement.getGeneratedKeys();
            keys.next();
            message.setId(keys.getInt(1));
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException();
        }
        return message;
    }

    @Override
    public Optional<Message> get(Integer id, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_MESSAGE)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(getMessage(resultSet));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException();
        }
        return Optional.empty();
    }

    @Override
    public boolean update(Integer id, Message message, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MESSAGE)) {
            preparedStatement.setInt(1, message.getChatId());
            preparedStatement.setString(2, message.getSender());
            preparedStatement.setString(3, message.getText());
            preparedStatement.setTimestamp(4, message.getDateTime());
            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException();
        }
    }

    @Override
    public boolean delete(Integer id, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MESSAGE)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException();
        }
    }

    public List<Message> getChatMessages(int chatId, Connection connection) throws SQLException {
        List<Message> messages = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_MESSAGES_BY_CHAT_ID)) {
            preparedStatement.setInt(1, chatId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                messages.add(getMessage(resultSet));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException();
        }
        return messages;
    }

    private Message getMessage(ResultSet resultSet) throws SQLException {
        Message message = new Message();
        message.setId(resultSet.getInt(1));
        message.setChatId(resultSet.getInt(2));
        message.setSender(resultSet.getString(3));
        message.setText(resultSet.getString(4));
        message.setDateTime(resultSet.getTimestamp(5));
        return message;
    }
}
