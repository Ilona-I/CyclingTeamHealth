package ua.nure.illiashenko.ilona.dao;

import ua.nure.illiashenko.ilona.dao.entities.UserTeam;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserTeamDAO implements DAO<UserTeam, Integer> {

    @Override
    public boolean insert(UserTeam userTeam, Connection connection) throws SQLException {
        return false;
    }

    @Override
    public UserTeam get(Integer key, Connection connection) throws SQLException {
        return null;
    }

    @Override
    public boolean update(Integer key, UserTeam newEntity, Connection connection) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Integer key, Connection connection) throws SQLException {
        return false;
    }

    public List<UserTeam> getAll(Connection connection) throws SQLException {
        return null;
    }

    public boolean deleteTeam(Integer teamId, Connection connection) throws SQLException {
        return false;
    }

    public boolean delete(String login, int teamId, Connection connection) throws SQLException {
        return false;
    }
}
