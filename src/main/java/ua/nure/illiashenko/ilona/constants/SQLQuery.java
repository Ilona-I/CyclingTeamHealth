package ua.nure.illiashenko.ilona.constants;

public class SQLQuery {

    public static final String INSERT_USER = "INSERT INTO `user` (`login`, `password`, `first_name`, `last_name`, `email`, `role`, `team_id`, `birth_date`, `height`, `weight`, `gender`, `status`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    public static final String UPDATE_USER = "UPDATE `cycling_team_health`.`user` SET `login` = ?, `password` = ?, `first_name` = ?, `last_name` = ?, `email` = ?, `role` = ?, `team_id` = ?, `birth_date` = ?, `height` = ?, `weight` = ?, `gender` = ?, `status` = ? WHERE (`login` = ?);";
    public static final String DELETE_USER = "DELETE FROM `cycling_team_health`.`user` WHERE (`login` = ?);";
    public static final String GET_USER_BY_LOGIN = "SELECT * FROM `user` WHERE `login` = ?;";

    public static final String INSERT_TEAM = "INSERT INTO `team` (`name`) VALUES (?)";
    public static final String UPDATE_TEAM = "UPDATE `cycling_team_health`.`team` SET `name` = ? WHERE (`id` = ?);";
    public static final String DELETE_TEAM = "DELETE FROM `cycling_team_health`.`team` WHERE (`id` = ?);";
    public static final String GET_TEAM = "SELECT * FROM cycling_team_health.team WHERE `id` = ?;";
    public static final String GET_TEAM_MEMBERS = "SELECT * FROM `user` WHERE `team_id` = ?;";

    public static final String INSERT_CHAT = "INSERT INTO `cycling_team_health`.`chat` (`type`, `name`) VALUES (?, ?);";
    public static final String GET_CHAT = "SELECT * FROM `cycling_team_health`.`chat` WHERE `id` = ?;";
    public static final String DELETE_CHAT = "DELETE FROM `cycling_team_health`.`chat` WHERE (`id` = ?);";
    public static final String GET_USERS_CHAT_ID = "SELECT `chat_id` FROM `user_chat` WHERE `login` = ? AND `chat_id` in (SELECT `chat_id` FROM `user_chat` WHERE `login` = ?);";

    public static final String INSERT_USER_CHAT = "INSERT INTO `cycling_team_health`.`user_chat` (`chat_id`, `login`) VALUES (?, ?);";
    public static final String DELETE_USER_CHAT = "DELETE FROM `cycling_team_health`.`user_chat` WHERE (`id` = ?);";
    public static final String GET_USER_CHATS = "SELECT id, name FROM cycling_team_health.chat WHERE id in (SELECT chat_id FROM user_chat where `login` = ?);";

    public static final String INSERT_MESSAGE = "INSERT INTO `cycling_team_health`.`message` (`chat_id`, `sender`, `text`, `datetime`) VALUES (?, ?, ?, ?);";
    public static final String GET_MESSAGE = "SELECT * FROM `cycling_team_health`.`message` WHERE `id` = ?;";
    public static final String UPDATE_MESSAGE = "UPDATE `cycling_team_health`.`message` SET `chat_id` = ?, `sender` = ?, `text` = ?, `datetime` = ? WHERE (`id` = ?);";
    public static final String DELETE_MESSAGE = "DELETE FROM `cycling_team_health`.`message` WHERE (`id` = ?);";
    public static final String GET_MESSAGES_BY_CHAT_ID = "SELECT * FROM `cycling_team_health`.`message` WHERE `chat_id` = ?;";

    public static final String INSERT_FEEDBACK = "INSERT INTO `cycling_team_health`.`feedback` (`login`, `datetime`, `rating`, `text`, `status`) VALUES (?, ?, ?, ?, ?);";
    public static final String UPDATE_FEEDBACK = "UPDATE `cycling_team_health`.`feedback` SET `login` = ?, `datetime` = ?, `rating` = ?, `text` = ?, `status` = ? WHERE (`id` = ?);";
    public static final String DELETE_FEEDBACK = "DELETE FROM `cycling_team_health`.`feedback` WHERE (`id` = ?);";
    public static final String GET_FEEDBACKS = "SELECT * FROM cycling_team_health.feedback;";
    public static final String GET_FEEDBACK = "SELECT * FROM cycling_team_health.feedback WHERE `id` = ?;";

    public static final String INSERT_TRAINING_GOALS = "INSERT INTO `cycling_team_health`.`training_goals` (`team_id`, `pulse`, `speed`, `start_datetime`, `end_datetime`) VALUES (?, ?, ?, ?, ?);";
    public static final String UPDATE_TRAINING_GOALS = "UPDATE `cycling_team_health`.`training_goals` SET `team_id` = ?, `pulse` = ?, `speed` = ?, `start_datetime` = ?, `end_datetime` = ? WHERE (`id` = ?);";
    public static final String DELETE_TRAINING_GOALS = "DELETE FROM `cycling_team_health`.`training_goals` WHERE (`id` = ?);";
    public static final String GET_TRAINING_GOALS = "SELECT * FROM cycling_team_health.training_goals WHERE `id` = ?;";
    public static final String GET_TEAM_TRAININGS = "SELECT * FROM cycling_team_health.training_goals WHERE `team_id` = ?;";

    public static final String INSERT_TRAINING_RESULTS = "INSERT INTO `cycling_team_health`.`training_results` (`training_id`, `login`, `pulse`, `speed`) VALUES (?, ?, ?, ?);";
    public static final String GET_TRAINING_RESULTS = "SELECT * FROM cycling_team_health.training_results WHERE `id` = ?;";

    public static final String GET_USER_TRAINING_RESULTS = "SELECT * FROM cycling_team_health.training_results WHERE `login` = ? AND `id` = ?;";
    public static final String GET_USER_TRAININGS_RESULTS = "SELECT * FROM cycling_team_health.training_results WHERE `login` = ?;";
    public static final String GET_TEAM_TRAINING_RESULTS = "SELECT * FROM cycling_team_health.training_results WHERE `training_id` = (SELECT id FROM `cycling_team_health`.`training_goals` WHERE `team_id` = ?);";
    public static final String GET_TEAMS_RATINGS = "SELECT name, " +
            " ((SELECT sum(training_results.speed) from training_results" +
            " where training_id in" +
            " (SELECT id from training_goals where training_goals.team_id=team.id)))*" +
            " (SELECT sum(UNIX_TIMESTAMP(end_datetime)-UNIX_TIMESTAMP(start_datetime)) from training_goals " +
            " where training_goals.team_id=team.id" +
            " ) as rating" +
            " from team group by name ORDER BY rating  DESC;";
    public static final String GET_ALL_USERS = "SELECT * FROM `user`;";

    private SQLQuery() {
    }
}
