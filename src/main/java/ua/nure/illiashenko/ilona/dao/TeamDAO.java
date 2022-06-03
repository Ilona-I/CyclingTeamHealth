package ua.nure.illiashenko.ilona.dao;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.illiashenko.ilona.dao.entities.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static ua.nure.illiashenko.ilona.constants.SQLQuery.DELETE_TEAM;
import static ua.nure.illiashenko.ilona.constants.SQLQuery.GET_TEAM;
import static ua.nure.illiashenko.ilona.constants.SQLQuery.GET_TEAMS_RATINGS;
import static ua.nure.illiashenko.ilona.constants.SQLQuery.INSERT_TEAM;
import static ua.nure.illiashenko.ilona.constants.SQLQuery.UPDATE_TEAM;

public class TeamDAO implements DAO<Team, Integer> {

    private static final Logger logger = LoggerFactory.getLogger(TeamDAO.class);

    @Override
    public Team insert(Team team, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TEAM, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, team.getName());
            preparedStatement.executeUpdate();
            ResultSet keys = preparedStatement.getGeneratedKeys();
            keys.next();
            team.setId(keys.getInt(1));
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException();
        }
        return team;
    }

    @Override
    public Optional<Team> get(Integer id, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_TEAM)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Team team = new Team();
                team.setId(resultSet.getInt(1));
                team.setName(resultSet.getString(2));
                return Optional.of(team);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException();
        }
        return Optional.empty();
    }

    @Override
    public boolean update(Integer id, Team team, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TEAM)) {
            preparedStatement.setString(1, team.getName());
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException();
        }
    }

    @Override
    public boolean delete(Integer id, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TEAM)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException();
        }
    }

    public Map<String, Integer> getTeamsRatings(Connection connection) throws SQLException {
        Map<String, Integer> teamsRatings = new HashMap<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_TEAMS_RATINGS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                teamsRatings.put(resultSet.getString(1), resultSet.getInt(2));
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new SQLException();
        }
        return teamsRatings;
    }
}
