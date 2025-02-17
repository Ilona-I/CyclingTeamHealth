package ua.nure.illiashenko.ilona.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.illiashenko.ilona.dao.TrainingGoalsDAO;
import ua.nure.illiashenko.ilona.dao.TrainingResultsDAO;
import ua.nure.illiashenko.ilona.dao.entities.TrainingGoals;
import ua.nure.illiashenko.ilona.dao.entities.TrainingResults;
import ua.nure.illiashenko.ilona.exceptions.CannotDoTransactionException;
import ua.nure.illiashenko.ilona.exceptions.training.CannotAddTrainingException;
import ua.nure.illiashenko.ilona.exceptions.training.CannotDeleteTrainingException;
import ua.nure.illiashenko.ilona.exceptions.training.CannotUpdateTrainingException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class TrainingService {

    private static final Logger logger = LoggerFactory.getLogger(TrainingService.class);
    private final TrainingGoalsDAO trainingGoalsDAO;
    private final TrainingResultsDAO trainingResultsDAO;
    private final TransactionManager transactionManager;

    public TrainingService(TrainingGoalsDAO trainingGoalsDAO, TrainingResultsDAO trainingResultsDAO, TransactionManager transactionManager) {
        this.trainingGoalsDAO = trainingGoalsDAO;
        this.trainingResultsDAO = trainingResultsDAO;
        this.transactionManager = transactionManager;
    }

    public boolean addTraining(TrainingGoals trainingGoals) {
        Function<Connection, Boolean> function = connection -> {
            try {
                trainingGoalsDAO.insert(trainingGoals, connection);
                return true;
            } catch (SQLException e) {
                throw new CannotAddTrainingException(e.getMessage());
            }
        };
        return doInTransaction(function);
    }

    public boolean addTrainingResults(TrainingResults trainingResults) {
        Function<Connection, Boolean> function = connection -> {
            try {
                trainingResultsDAO.insert(trainingResults, connection);
                return true;
            } catch (SQLException e) {
                throw new CannotAddTrainingException(e.getMessage());
            }
        };
        return doInTransaction(function);
    }

    public Optional<TrainingResults> getUserTrainingResults(String login, int trainingId) {
        Function<Connection, Optional<TrainingResults>> function = connection -> {
            try {
                return trainingResultsDAO.get(login, trainingId, connection);
            } catch (SQLException e) {
                throw new CannotAddTrainingException(e.getMessage());
            }
        };
        return doInTransaction(function);
    }

    public List<TrainingResults> getTeamTrainingResults(int trainingId) {
        Function<Connection, List<TrainingResults>> function = connection -> {
            try {
                return trainingResultsDAO.getTeamTrainingResults(trainingId, connection);
            } catch (SQLException e) {
                throw new CannotAddTrainingException(e.getMessage());
            }
        };
        return doInTransaction(function);
    }

    public List<TrainingResults> getUserTrainingsResults(String login) {
        Function<Connection, List<TrainingResults>> function = connection -> {
            try {
                return trainingResultsDAO.getUserTrainingsResults(login, connection);
            } catch (SQLException e) {
                throw new CannotAddTrainingException(e.getMessage());
            }
        };
        return doInTransaction(function);
    }

    public List<TrainingResults> getTeamTrainingsResults(int teamId) {
        Function<Connection, List<TrainingResults>> function = connection -> {
            try {
                return trainingResultsDAO.getTeamTrainingsResults(teamId, connection);
            } catch (SQLException e) {
                throw new CannotAddTrainingException(e.getMessage());
            }
        };
        return doInTransaction(function);
    }

    public List<TrainingGoals> getAllTeamTrainings(int teamId) {
        Function<Connection, List<TrainingGoals>> function = connection -> {
            try {
                return trainingGoalsDAO.getTeamTrainings(teamId, connection);
            } catch (SQLException e) {
                throw new CannotAddTrainingException(e.getMessage());
            }
        };
        return doInTransaction(function);
    }

    public boolean isTrainingWithSuchIdExists(int id) {
        Function<Connection, Boolean> function = connection -> {
            try {
                return trainingGoalsDAO.get(id, connection).isPresent();
            } catch (SQLException e) {
                throw new CannotUpdateTrainingException(e.getMessage());
            }
        };
        return doInTransaction(function);
    }

    public Optional<TrainingGoals> getTrainingGoals(int id) {
        Function<Connection, Optional<TrainingGoals>> function = connection -> {
            try {
                return trainingGoalsDAO.get(id, connection);
            } catch (SQLException e) {
                throw new CannotUpdateTrainingException(e.getMessage());
            }
        };
        return doInTransaction(function);
    }

    public boolean updateTrainingGoals(TrainingGoals trainingGoals) {
        Function<Connection, Boolean> function = connection -> {
            try {
                return trainingGoalsDAO.update(trainingGoals.getId(), trainingGoals, connection);
            } catch (SQLException e) {
                throw new CannotUpdateTrainingException(e.getMessage());
            }
        };
        return doInTransaction(function);
    }

    public boolean deleteTraining(int trainingGoalsId) {
        Function<Connection, Boolean> function = connection -> {
            try {
                return trainingGoalsDAO.delete(trainingGoalsId, connection);
            } catch (SQLException e) {
                throw new CannotDeleteTrainingException(e.getMessage());
            }
        };
        return doInTransaction(function);
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
