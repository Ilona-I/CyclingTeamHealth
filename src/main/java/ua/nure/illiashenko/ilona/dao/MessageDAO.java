package ua.nure.illiashenko.ilona.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.illiashenko.ilona.dao.entities.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static ua.nure.illiashenko.ilona.constants.SQLQuery.DELETE_MESSAGE;
import static ua.nure.illiashenko.ilona.constants.SQLQuery.GET_MESSAGE;
import static ua.nure.illiashenko.ilona.constants.SQLQuery.INSERT_MESSAGE;
import static ua.nure.illiashenko.ilona.constants.SQLQuery.UPDATE_MESSAGE;

public class MessageDAO implements DAO <Message, Integer> {

    private static final Logger logger = LoggerFactory.getLogger(MessageDAO.class);

    @Override
    public boolean insert(Message message, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MESSAGE)) {
            preparedStatement.setInt(1, message.getChatId());
            preparedStatement.setString(2, message.getSender());
            preparedStatement.setString(3, message.getText());
            preparedStatement.setTimestamp(4, message.getDateTime());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException();
        }
    }

    @Override
    public Optional<Message> get(Integer id, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_MESSAGE)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Message message = new Message();
                message.setId(resultSet.getInt(1));
                message.setChatId(resultSet.getInt(2));
                message.setSender(resultSet.getString(3));
                message.setText(resultSet.getString(4));
                message.setDateTime(resultSet.getTimestamp(5));
                return Optional.of(message);
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
}
