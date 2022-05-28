package ua.nure.illiashenko.ilona.constants;

public class SQLQuery {

    public static final String INSERT_USER = "INSERT INTO `user` (`login`, `password`, `first_name`, `last_name`, `email`, `role`, `team_id`, `birth_date`, `height`, `weight`, `gender`, `status`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    public static final String UPDATE_USER = "";
    public static final String DELETE_USER = "";
    public static final String GET_USER_BY_LOGIN = "SELECT * FROM `user` WHERE `login` = ?;";

    public static final String INSERT_TEAM = "INSERT INTO `team` (`name`) VALUES (?)";
    public static final String UPDATE_TEAM = "";
    public static final String DELETE_TEAM = "";
    public static final String GET_TEAM = "";

    public static final String ADD_USER_TO_THE_TEAM = "";
    public static final String DELETE_USER_FROM_THE_TEAM = "";
    public static final String GET_TEAM_MEMBERS = "";
    public static final String GET_USER_TEAMS = "";

    public static final String INSERT_CHAT = "";
    public static final String GET_CHAT = "";
    public static final String DELETE_CHAT = "";

    public static final String INSERT_USER_CHAT = "";
    public static final String DELETE_USER_CHAT = "";

    public static final String INSERT_MESSAGE = "";
    public static final String GET_MESSAGE = "";
    public static final String UPDATE_MESSAGE = "";
    public static final String DELETE_MESSAGE = "";
    public static final String GET_MESSAGES_BY_CHAT_ID = "";

    public static final String INSERT_FEEDBACK = "";
    public static final String UPDATE_FEEDBACK = "";
    public static final String DELETE_FEEDBACK = "";
    public static final String GET_FEEDBACKS = "";
    public static final String GET_FEEDBACK = "";

    public static final String INSERT_TRAINING_GOALS = "";
    public static final String UPDATE_TRAINING_GOALS = "";
    public static final String DELETE_TRAINING_GOALS = "";
    public static final String GET_TRAINING_GOALS = "";

    public static final String INSERT_TRAINING_RESULTS = "";
    public static final String GET_TRAINING_RESULTS = "";

    public static final String GET_USER_CHATS = "";

    private SQLQuery() {
    }
}
