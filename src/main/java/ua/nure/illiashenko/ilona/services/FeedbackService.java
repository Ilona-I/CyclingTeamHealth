package ua.nure.illiashenko.ilona.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.illiashenko.ilona.TransactionManager;
import ua.nure.illiashenko.ilona.dao.FeedbackDAO;

public class FeedbackService {

    private static final Logger logger = LoggerFactory.getLogger(FeedbackService.class);
    private final FeedbackDAO feedbackDAO;
    private final TransactionManager transactionManager;

    public FeedbackService(FeedbackDAO feedbackDAO, TransactionManager transactionManager){
        this.feedbackDAO = feedbackDAO;
        this.transactionManager = transactionManager;
    }
}
