package ua.nure.illiashenko.ilona.dao;

import com.mysql.cj.jdbc.exceptions.OperationNotSupportedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.illiashenko.ilona.dao.entities.Chat;
import ua.nure.illiashenko.ilona.dao.entities.Feedback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static ua.nure.illiashenko.ilona.constants.SQLQuery.DELETE_CHAT;
import static ua.nure.illiashenko.ilona.constants.SQLQuery.DELETE_FEEDBACK;
import static ua.nure.illiashenko.ilona.constants.SQLQuery.GET_CHAT;
import static ua.nure.illiashenko.ilona.constants.SQLQuery.GET_FEEDBACK;
import static ua.nure.illiashenko.ilona.constants.SQLQuery.INSERT_CHAT;
import static ua.nure.illiashenko.ilona.constants.SQLQuery.INSERT_FEEDBACK;
import static ua.nure.illiashenko.ilona.constants.SQLQuery.UPDATE_FEEDBACK;

public class ChatDAO implements DAO<Chat, Integer> {

    private static final Logger logger = LoggerFactory.getLogger(ChatDAO.class);

    @Override
    public boolean insert(Chat chat, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CHAT)) {
            preparedStatement.setString(1, chat.getType());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException();
        }
    }

    @Override
    public Optional<Chat> get(Integer id, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_CHAT)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Chat chat = new Chat();
                chat.setId(resultSet.getInt(1));
                chat.setType(resultSet.getString(2));
                return Optional.of(chat);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException();
        }
        return Optional.empty();
    }

    @Override
    public boolean update(Integer id, Chat chat, Connection connection) throws SQLException {
        throw new OperationNotSupportedException();
    }

    @Override
    public boolean delete(Integer id, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CHAT)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException();
        }
    }
}
