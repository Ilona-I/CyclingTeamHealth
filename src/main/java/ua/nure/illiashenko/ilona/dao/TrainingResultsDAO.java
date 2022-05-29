package ua.nure.illiashenko.ilona.dao;

import com.mysql.cj.jdbc.exceptions.OperationNotSupportedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.illiashenko.ilona.dao.entities.TrainingResults;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ua.nure.illiashenko.ilona.constants.SQLQuery.GET_TEAM_TRAINING_RESULTS;
import static ua.nure.illiashenko.ilona.constants.SQLQuery.GET_TRAINING_RESULTS;
import static ua.nure.illiashenko.ilona.constants.SQLQuery.GET_USER_TRAININGS_RESULTS;
import static ua.nure.illiashenko.ilona.constants.SQLQuery.GET_USER_TRAINING_RESULTS;
import static ua.nure.illiashenko.ilona.constants.SQLQuery.INSERT_TRAINING_RESULTS;

public class TrainingResultsDAO implements DAO<TrainingResults, Integer> {

    private static final Logger logger = LoggerFactory.getLogger(TrainingResultsDAO.class);

    @Override
    public TrainingResults insert(TrainingResults trainingResults, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TRAINING_RESULTS, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, trainingResults.getTrainingId());
            preparedStatement.setString(2, trainingResults.getLogin());
            preparedStatement.setInt(3, trainingResults.getPulse());
            preparedStatement.setInt(4, trainingResults.getSpeed());
            preparedStatement.executeUpdate();
            ResultSet keys = preparedStatement.getGeneratedKeys();
            keys.next();
            trainingResults.setId(keys.getInt(1));
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException();
        }
        return trainingResults;
    }

    @Override
    public Optional<TrainingResults> get(Integer id, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_TRAINING_RESULTS)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                TrainingResults trainingResults = new TrainingResults();
                trainingResults.setId(resultSet.getInt(1));
                trainingResults.setTrainingId(resultSet.getInt(2));
                trainingResults.setLogin(resultSet.getString(3));
                trainingResults.setPulse(resultSet.getInt(4));
                trainingResults.setSpeed(resultSet.getInt(5));
                return Optional.of(trainingResults);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException();
        }
        return Optional.empty();
    }

    @Override
    public boolean update(Integer key, TrainingResults newEntity, Connection connection) throws SQLException {
        throw new OperationNotSupportedException();
    }

    @Override
    public boolean delete(Integer key, Connection connection) throws SQLException {
        throw new OperationNotSupportedException();
    }

    public Optional<TrainingResults> get(String login, Integer trainingId, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_TRAINING_RESULTS)) {
            preparedStatement.setString(1, login);
            preparedStatement.setInt(2, trainingId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                TrainingResults trainingResults = new TrainingResults();
                trainingResults.setId(resultSet.getInt(1));
                trainingResults.setTrainingId(resultSet.getInt(2));
                trainingResults.setLogin(resultSet.getString(3));
                trainingResults.setPulse(resultSet.getInt(4));
                trainingResults.setSpeed(resultSet.getInt(5));
                return Optional.of(trainingResults);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException();
        }
        return Optional.empty();
    }

    public List<TrainingResults> getTeamTrainingResults(Integer trainingId, Connection connection) throws SQLException {
        List<TrainingResults> trainingResultsList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_TEAM_TRAINING_RESULTS)) {
            preparedStatement.setInt(1, trainingId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TrainingResults trainingResults = new TrainingResults();
                trainingResults.setId(resultSet.getInt(1));
                trainingResults.setTrainingId(resultSet.getInt(2));
                trainingResults.setLogin(resultSet.getString(3));
                trainingResults.setPulse(resultSet.getInt(4));
                trainingResults.setSpeed(resultSet.getInt(5));
                trainingResultsList.add(trainingResults);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException();
        }
        return trainingResultsList;
    }

    public List<TrainingResults> getUserTrainingsResults(String login, Connection connection) throws SQLException {
        List<TrainingResults> trainingResultsList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_TRAININGS_RESULTS)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TrainingResults trainingResults = new TrainingResults();
                trainingResults.setId(resultSet.getInt(1));
                trainingResults.setTrainingId(resultSet.getInt(2));
                trainingResults.setLogin(resultSet.getString(3));
                trainingResults.setPulse(resultSet.getInt(4));
                trainingResults.setSpeed(resultSet.getInt(5));
                trainingResultsList.add(trainingResults);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException();
        }
        return trainingResultsList;
    }
}
