package ua.nure.illiashenko.ilona.dao;

import ua.nure.illiashenko.ilona.dao.entities.Team;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class TeamDAO implements DAO<Team, Integer>{
    @Override
    public boolean insert(Team team, Connection connection) throws SQLException {
        return false;
    }

    @Override
    public Optional<Team> get(Integer key, Connection connection) throws SQLException {
        return null;
    }

    @Override
    public boolean update(Integer key, Team newEntity, Connection connection) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(Integer key, Connection connection) throws SQLException {
        return false;
    }
}
