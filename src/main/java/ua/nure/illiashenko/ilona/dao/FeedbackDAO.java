package ua.nure.illiashenko.ilona.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.illiashenko.ilona.dao.entities.Feedback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ua.nure.illiashenko.ilona.constants.SQLQuery.DELETE_FEEDBACK;
import static ua.nure.illiashenko.ilona.constants.SQLQuery.GET_FEEDBACK;
import static ua.nure.illiashenko.ilona.constants.SQLQuery.GET_FEEDBACKS;
import static ua.nure.illiashenko.ilona.constants.SQLQuery.INSERT_FEEDBACK;
import static ua.nure.illiashenko.ilona.constants.SQLQuery.UPDATE_FEEDBACK;

public class FeedbackDAO implements DAO<Feedback, Integer> {

    private static final Logger logger = LoggerFactory.getLogger(FeedbackDAO.class);

    @Override
    public Feedback insert(Feedback feedback, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FEEDBACK, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, feedback.getLogin());
            preparedStatement.setTimestamp(2, feedback.getDateTime());
            preparedStatement.setInt(3, feedback.getRating());
            preparedStatement.setString(4, feedback.getText());
            preparedStatement.setString(5, feedback.getStatus());
            preparedStatement.executeUpdate();
            ResultSet keys = preparedStatement.getGeneratedKeys();
            keys.next();
            feedback.setId(keys.getInt(1));
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException();
        }
        return feedback;
    }

    @Override
    public Optional<Feedback> get(Integer id, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_FEEDBACK)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Feedback feedback = new Feedback();
                feedback.setId(resultSet.getInt(1));
                feedback.setLogin(resultSet.getString(2));
                feedback.setDateTime(resultSet.getTimestamp(3));
                feedback.setRating(resultSet.getInt(4));
                feedback.setText(resultSet.getString(5));
                feedback.setStatus(resultSet.getString(6));
                return Optional.of(feedback);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException();
        }
        return Optional.empty();
    }

    @Override
    public boolean update(Integer id, Feedback feedback, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_FEEDBACK)) {
            preparedStatement.setString(1, feedback.getLogin());
            preparedStatement.setTimestamp(2, feedback.getDateTime());
            preparedStatement.setInt(3, feedback.getRating());
            preparedStatement.setString(4, feedback.getText());
            preparedStatement.setString(5, feedback.getStatus());
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
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FEEDBACK)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException();
        }
    }

    public List<Feedback> getFeedbacks(Connection connection) throws SQLException {
        List<Feedback> feedbacks = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_FEEDBACKS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Feedback feedback = new Feedback();
                feedback.setId(resultSet.getInt(1));
                feedback.setLogin(resultSet.getString(2));
                feedback.setDateTime(resultSet.getTimestamp(3));
                feedback.setRating(resultSet.getInt(4));
                feedback.setText(resultSet.getString(5));
                feedback.setStatus(resultSet.getString(6));
                feedbacks.add(feedback);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException();
        }
        return feedbacks;
    }
}
