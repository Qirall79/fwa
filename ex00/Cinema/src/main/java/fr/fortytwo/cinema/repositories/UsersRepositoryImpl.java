package fr.fortytwo.cinema.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import fr.fortytwo.cinema.models.User;

@Component
public class UsersRepositoryImpl extends NamedParameterJdbcTemplate implements UsersRepository {

    private DataSource dataSource;

    public UsersRepositoryImpl(DataSource dataSource) {
        super(dataSource);
        this.dataSource = dataSource;
    }

    @Override
    public User findById(Long id) {
        User user = null;
        try {
            user = queryForObject("SELECT * FROM users WHERE id = %d".formatted(id), new HashMap<>(), new UserRowMapper());

        } catch (Exception e) {
        }

        return user;
    }
    
    @Override
    public Optional<User> findByPhoneNumber(String phoneNumber) {
        User user = null;
        try {
            user = queryForObject("SELECT * FROM users WHERE phone_number = '%s'".formatted(phoneNumber), new HashMap<>(), new UserRowMapper());
            return Optional.of(user);
        } catch (Exception e) {
        }

        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        List<User> users = query("SELECT * FROM users", new HashMap<>(), new UserRowMapper());

        return users;
    }

    @Override
    public void save(User entity) {
        try {
            update("INSERT INTO users (first_name, last_name, phone_number, password) VALUES ('%s', '%s', '%s', '%s')".formatted(entity.getFirstName(), entity.getLastName(), entity.getPhoneNumber(), entity.getPassword()), new HashMap<>());
        } catch (Exception e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }
    }

    @Override
    public void update(User entity) {

        try {
            update("UPDATE users SET first_name = '%s', last_name = '%s', phone_number = '%s', password = '%s' WHERE id = %d".formatted(entity.getFirstName(), entity.getLastName(), entity.getPhoneNumber(), entity.getPassword(), entity.getId()), new HashMap<>());
        } catch (Exception e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try {
            update("DELETE FROM users WHERE id = %d".formatted(id), new HashMap<>());
        } catch (Exception e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }
    }

    class UserRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new User(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"), rs.getString("phone_number"), rs.getString("password"));
        }
    }
}
