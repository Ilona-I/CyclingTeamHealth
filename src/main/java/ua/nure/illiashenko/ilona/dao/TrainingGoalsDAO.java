package ua.nure.illiashenko.ilona.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.illiashenko.ilona.dao.entities.TrainingGoals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static ua.nure.illiashenko.ilona.constants.SQLQuery.DELETE_TRAINING_GOALS;
import static ua.nure.illiashenko.ilona.constants.SQLQuery.GET_TRAINING_GOALS;
import static ua.nure.illiashenko.ilona.constants.SQLQuery.INSERT_TRAINING_GOALS;
import static ua.nure.illiashenko.ilona.constants.SQLQuery.UPDATE_TRAINING_GOALS;

public class TrainingGoalsDAO implements DAO<TrainingGoals, Integer> {

    private static final Logger logger = LoggerFactory.getLogger(TrainingGoalsDAO.class);

    @Override
    public boolean insert(TrainingGoals trainingGoals, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TRAINING_GOALS)) {
            preparedStatement.setInt(1, trainingGoals.getTeamId());
            preparedStatement.setInt(2, trainingGoals.getPulse());
            preparedStatement.setInt(3, trainingGoals.getSpeed());
            preparedStatement.setTimestamp(4, trainingGoals.getStartDateTime());
            preparedStatement.setTimestamp(5, trainingGoals.getEndDateTime());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException();
        }
    }

    @Override
    public Optional<TrainingGoals> get(Integer id, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_TRAINING_GOALS)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                TrainingGoals trainingGoals = new TrainingGoals();
                trainingGoals.setId(resultSet.getInt(1));
                trainingGoals.setTeamId(resultSet.getInt(2));
                trainingGoals.setPulse(resultSet.getInt(3));
                trainingGoals.setSpeed(resultSet.getInt(4));
                trainingGoals.setStartDateTime(resultSet.getTimestamp(5));
                trainingGoals.setEndDateTime(resultSet.getTimestamp(6));
                return Optional.of(trainingGoals);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException();
        }
        return Optional.empty();
    }

    @Override
    public boolean update(Integer id, TrainingGoals trainingGoals, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TRAINING_GOALS)) {
            preparedStatement.setInt(1, trainingGoals.getTeamId());
            preparedStatement.setInt(2, trainingGoals.getPulse());
            preparedStatement.setInt(3, trainingGoals.getSpeed());
            preparedStatement.setTimestamp(4, trainingGoals.getStartDateTime());
            preparedStatement.setTimestamp(5, trainingGoals.getEndDateTime());
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
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TRAINING_GOALS)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException();
        }
    }
}
