package ua.nure.illiashenko.ilona.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.illiashenko.ilona.dao.DatabaseManager;
import ua.nure.illiashenko.ilona.services.AdminService;
import ua.nure.illiashenko.ilona.services.TransactionManager;
import ua.nure.illiashenko.ilona.dao.ChatDAO;
import ua.nure.illiashenko.ilona.dao.DBConnectionPool;
import ua.nure.illiashenko.ilona.dao.FeedbackDAO;
import ua.nure.illiashenko.ilona.dao.MessageDAO;
import ua.nure.illiashenko.ilona.dao.TeamDAO;
import ua.nure.illiashenko.ilona.dao.TrainingGoalsDAO;
import ua.nure.illiashenko.ilona.dao.TrainingResultsDAO;
import ua.nure.illiashenko.ilona.dao.UserChatDAO;
import ua.nure.illiashenko.ilona.dao.UserDAO;
import ua.nure.illiashenko.ilona.services.ChatService;
import ua.nure.illiashenko.ilona.services.DataValidator;
import ua.nure.illiashenko.ilona.services.FeedbackService;
import ua.nure.illiashenko.ilona.services.TeamService;
import ua.nure.illiashenko.ilona.services.TrainingService;
import ua.nure.illiashenko.ilona.services.UserService;
import ua.nure.illiashenko.ilona.utils.Base64Util;
import ua.nure.illiashenko.ilona.utils.MD5Util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import static ua.nure.illiashenko.ilona.constants.ContextConstants.ADMIN_SERVICE;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.BASE_64_UTIL;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.CHAT_SERVICE;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.DATA_VALIDATOR;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.FEEDBACK_SERVICE;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.MD_5_UTIL;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.RESPONSE_WRITER;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.TEAM_SERVICE;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.TRAINING_SERVICE;
import static ua.nure.illiashenko.ilona.constants.ContextConstants.USER_SERVICE;

public class ContextListener implements ServletContextListener {

    private final Logger logger = LoggerFactory.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        DBConnectionPool dbConnectionPool = new DBConnectionPool();
        TransactionManager transactionManager = new TransactionManager(dbConnectionPool);

        UserDAO userDAO = new UserDAO();
        ChatDAO chatDAO = new ChatDAO();
        FeedbackDAO feedbackDAO = new FeedbackDAO();
        MessageDAO messageDAO = new MessageDAO();
        TeamDAO teamDAO = new TeamDAO();
        TrainingGoalsDAO trainingGoalsDAO = new TrainingGoalsDAO();
        TrainingResultsDAO trainingResultsDAO = new TrainingResultsDAO();
        UserChatDAO userChatDAO = new UserChatDAO();
        DatabaseManager databaseManager = new DatabaseManager(servletContext.getInitParameter("backupPath"), servletContext.getInitParameter("mySqlBinPath"));
        MD5Util md5Util = new MD5Util();

        UserService userService = new UserService(userDAO, transactionManager, md5Util);
        TeamService teamService = new TeamService(teamDAO, userDAO, transactionManager);
        ChatService chatService = new ChatService(chatDAO, userChatDAO, messageDAO, transactionManager);
        FeedbackService feedbackService = new FeedbackService(feedbackDAO, transactionManager);
        TrainingService trainingService = new TrainingService(trainingGoalsDAO, trainingResultsDAO, transactionManager);
        AdminService adminService = new AdminService(databaseManager);

        servletContext.setAttribute(USER_SERVICE, userService);
        servletContext.setAttribute(TEAM_SERVICE, teamService);
        servletContext.setAttribute(CHAT_SERVICE, chatService);
        servletContext.setAttribute(FEEDBACK_SERVICE, feedbackService);
        servletContext.setAttribute(TRAINING_SERVICE, trainingService);
        servletContext.setAttribute(ADMIN_SERVICE, adminService);
        servletContext.setAttribute(DATA_VALIDATOR, new DataValidator());
        servletContext.setAttribute(RESPONSE_WRITER, new ResponseWriter());
        servletContext.setAttribute(BASE_64_UTIL, new Base64Util());
        servletContext.setAttribute(MD_5_UTIL, md5Util);
        logger.info("Context initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.info("Context destroyed");
    }
}
