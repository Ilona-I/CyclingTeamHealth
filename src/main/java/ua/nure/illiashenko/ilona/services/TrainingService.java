package ua.nure.illiashenko.ilona.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.illiashenko.ilona.TransactionManager;
import ua.nure.illiashenko.ilona.dao.TrainingGoalsDAO;
import ua.nure.illiashenko.ilona.dao.TrainingResultsDAO;

public class TrainingService {

    private static final Logger logger = LoggerFactory.getLogger(TrainingService.class);
    private final TrainingGoalsDAO trainingGoalsDAO;
    private final TrainingResultsDAO trainingResultsDAO;
    private final TransactionManager transactionManager;

    public TrainingService(TrainingGoalsDAO trainingGoalsDAO, TrainingResultsDAO trainingResultsDAO, TransactionManager transactionManager){
       this.trainingGoalsDAO = trainingGoalsDAO;
       this.trainingResultsDAO = trainingResultsDAO;
       this.transactionManager = transactionManager;
    }
}
