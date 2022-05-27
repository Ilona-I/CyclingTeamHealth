package ua.nure.illiashenko.ilona.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public interface DAO<Entity, KeyType> {

    Entity insert(Entity entity, Connection connection) throws SQLException;
    Optional<Entity> get(KeyType key, Connection connection) throws  SQLException;
    boolean update(KeyType key, Entity newEntity, Connection connection) throws SQLException;
    boolean delete(KeyType key, Connection connection) throws SQLException;
}
