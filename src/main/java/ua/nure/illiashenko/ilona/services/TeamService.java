package ua.nure.illiashenko.ilona.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.illiashenko.ilona.dao.TeamDAO;
import ua.nure.illiashenko.ilona.dao.UserDAO;
import ua.nure.illiashenko.ilona.dao.entities.Team;
import ua.nure.illiashenko.ilona.dao.entities.User;
import ua.nure.illiashenko.ilona.exceptions.team.CannotCreateTeamException;
import ua.nure.illiashenko.ilona.exceptions.team.CannotDeleteTeamException;
import ua.nure.illiashenko.ilona.exceptions.CannotDoTransactionException;
import ua.nure.illiashenko.ilona.exceptions.team.CannotFindTeamException;
import ua.nure.illiashenko.ilona.exceptions.team.CannotFindTeamMembersException;
import ua.nure.illiashenko.ilona.exceptions.team.CannotUpdateTeamException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class TeamService {

    private static final Logger logger = LoggerFactory.getLogger(TeamService.class);
    private final TeamDAO teamDAO;
    private final UserDAO userDAO;
    private final TransactionManager transactionManager;

    public TeamService(TeamDAO teamDAO, UserDAO userDAO, TransactionManager transactionManager) {
        this.teamDAO = teamDAO;
        this.userDAO = userDAO;
        this.transactionManager = transactionManager;
    }

    public boolean createTeam(Team team) {
        Function<Connection, Boolean> function = connection -> {
            try {
                teamDAO.insert(team, connection);
                return true;
            } catch (SQLException e) {
                throw new CannotCreateTeamException(e.getMessage());
            }
        };
        return doInTransaction(function);
    }

    public boolean isTeamWithSuchIdExists(int id) {
        Function<Connection, Optional<Team>> function = connection -> {
            try {
                return teamDAO.get(id, connection);
            } catch (SQLException e) {
                logger.error(e.getMessage());
                throw new CannotFindTeamException(e.getMessage());
            }
        };
        return doInTransaction(function).isPresent();
    }

    public boolean updateTeam(int id, Team team) {
        Function<Connection, Boolean> function = connection -> {
            try {
                teamDAO.update(id, team, connection);
                return true;
            } catch (SQLException e) {
                throw new CannotUpdateTeamException(e.getMessage());
            }
        };
        return doInTransaction(function);
    }

    public boolean deleteTeam(int teamId) {
        Function<Connection, Boolean> function = connection -> {
            try {

                teamDAO.delete(teamId, connection);
                return true;
            } catch (SQLException e) {
                throw new CannotDeleteTeamException(e.getMessage());
            }
        };
        return doInTransaction(function);
    }

    public List<User> getTeamMembers(int teamId) {
        Function<Connection, List<User>> function = connection -> {
            try {
                return userDAO.getTeamMembers(teamId, connection);
            } catch (SQLException e) {
                throw new CannotFindTeamMembersException(e.getMessage());
            }
        };
        return doInTransaction(function);
    }

    public Map<String, Integer> getTeams() {
        return null;
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
