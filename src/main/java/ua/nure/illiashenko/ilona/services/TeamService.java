package ua.nure.illiashenko.ilona.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.illiashenko.ilona.dao.TeamDAO;
import ua.nure.illiashenko.ilona.dao.UserTeamDAO;
import ua.nure.illiashenko.ilona.dao.entities.Team;
import ua.nure.illiashenko.ilona.dao.entities.UserTeam;
import ua.nure.illiashenko.ilona.exceptions.team.CannotAddUserToTheTeamException;
import ua.nure.illiashenko.ilona.exceptions.team.CannotCreateTeamException;
import ua.nure.illiashenko.ilona.exceptions.team.CannotDeleteTeamException;
import ua.nure.illiashenko.ilona.exceptions.CannotDoTransactionException;
import ua.nure.illiashenko.ilona.exceptions.team.CannotDeleteUserFromTeamException;
import ua.nure.illiashenko.ilona.exceptions.team.CannotFindTeamMembersException;
import ua.nure.illiashenko.ilona.exceptions.team.CannotUpdateTeamException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class TeamService {

    private static final Logger logger = LoggerFactory.getLogger(TeamService.class);
    private final TeamDAO teamDAO;
    private final UserTeamDAO userTeamDAO;
    private final TransactionManager transactionManager;

    public TeamService(TeamDAO teamDAO, UserTeamDAO userTeamDAO, TransactionManager transactionManager) {
        this.teamDAO = teamDAO;
        this.userTeamDAO = userTeamDAO;
        this.transactionManager = transactionManager;
    }

    public boolean createTeam(Team team, UserTeam userTeam) {
        Function<Connection, Boolean> function = connection -> {
            try {
                teamDAO.insert(team, connection);
                userTeamDAO.insert(userTeam, connection);
                return true;
            } catch (SQLException e) {
                throw new CannotCreateTeamException(e.getMessage());
            }
        };
        return doInTransaction(function);
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
                return userTeamDAO.deleteTeam(teamId, connection) && teamDAO.delete(teamId, connection);
            } catch (SQLException e) {
                throw new CannotDeleteTeamException(e.getMessage());
            }
        };
        return doInTransaction(function);
    }

    public boolean addUserToTheTeam(UserTeam userTeam) {
        Function<Connection, Boolean> function = connection -> {
            try {
                return userTeamDAO.insert(userTeam, connection);
            } catch (SQLException e) {
                throw new CannotAddUserToTheTeamException(e.getMessage());
            }
        };
        return doInTransaction(function);
    }

    public boolean deleteUserFromTheTeam(String login, int teamId) {
        Function<Connection, Boolean> function = connection -> {
            try {
                userTeamDAO.delete(login, teamId, connection);
                return true;
            } catch (SQLException e) {
                throw new CannotDeleteUserFromTeamException(e.getMessage());
            }
        };
        return doInTransaction(function);
    }

    public List<UserTeam> getTeamMembers(int teamId) {
        Function<Connection, List<UserTeam>> function = connection -> {
            try {
                return userTeamDAO.getAll(connection);
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
