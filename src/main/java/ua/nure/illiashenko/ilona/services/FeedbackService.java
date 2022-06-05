package ua.nure.illiashenko.ilona.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.illiashenko.ilona.dao.FeedbackDAO;
import ua.nure.illiashenko.ilona.dao.entities.Feedback;
import ua.nure.illiashenko.ilona.exceptions.CannotDoTransactionException;
import ua.nure.illiashenko.ilona.exceptions.feedback.CannotAddFeedbackException;
import ua.nure.illiashenko.ilona.exceptions.feedback.CannotDeleteFeedbackException;
import ua.nure.illiashenko.ilona.exceptions.feedback.CannotFindFeedbacksException;
import ua.nure.illiashenko.ilona.exceptions.feedback.CannotUpdateFeedbackException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Function;

public class FeedbackService {

    private static final Logger logger = LoggerFactory.getLogger(FeedbackService.class);
    private final FeedbackDAO feedbackDAO;
    private final TransactionManager transactionManager;

    public FeedbackService(FeedbackDAO feedbackDAO, TransactionManager transactionManager) {
        this.feedbackDAO = feedbackDAO;
        this.transactionManager = transactionManager;
    }

    public List<String> getFeedbacks() {
        Function<Connection, List<String>> function = connection -> {
            try {
                return feedbackDAO.getFeedbacks(connection);
            } catch (SQLException e) {
                throw new CannotFindFeedbacksException(e.getMessage());
            }
        };
        return doInTransaction(function);
    }

    public boolean createFeedback(Feedback feedback) {
        Function<Connection, Boolean> function = connection -> {
            try {
                feedbackDAO.insert(feedback, connection);
                return true;
            } catch (SQLException e) {
                throw new CannotAddFeedbackException(e.getMessage());
            }
        };
        return doInTransaction(function);
    }

    public boolean updateFeedback(Feedback feedback) {
        Function<Connection, Boolean> function = connection -> {
            try {
                return feedbackDAO.update(feedback.getId(), feedback, connection);
            } catch (SQLException e) {
                throw new CannotUpdateFeedbackException(e.getMessage());
            }
        };
        return doInTransaction(function);
    }

    public boolean deleteFeedback(int feedbackId) {
        Function<Connection, Boolean> function = connection -> {
            try {
                return feedbackDAO.delete(feedbackId, connection);
            } catch (SQLException e) {
                throw new CannotDeleteFeedbackException(e.getMessage());
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
