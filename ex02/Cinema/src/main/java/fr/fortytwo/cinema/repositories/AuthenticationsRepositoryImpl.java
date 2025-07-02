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

import fr.fortytwo.cinema.models.Authentication;
import fr.fortytwo.cinema.models.User;

@Component
public class AuthenticationsRepositoryImpl extends NamedParameterJdbcTemplate implements AuthenticationsRepository {

    private DataSource dataSource;

    public AuthenticationsRepositoryImpl(DataSource dataSource) {
        super(dataSource);
        this.dataSource = dataSource;
    }

    @Override
    public Authentication findById(Long id) {
        return null;
    }

    @Override
    public List<Authentication> findAll() {
        List<Authentication> authentications = query("SELECT * FROM authentications", new HashMap<>(), new AuthenticationRowMapper());

        return authentications;
    }
    
    @Override
    public List<Authentication> findByUserId(Long id) {
        List<Authentication> authentications = query("SELECT * FROM authentications WHERE user_id = %d".formatted(id), new HashMap<>(), new AuthenticationRowMapper());

        return authentications;
    }

    

    @Override
    public void save(Authentication entity) {
        try {
            update("INSERT INTO authentications (ip, user_id) VALUES ('%s', %d)".formatted(entity.getIp(), entity.getUserId()), new HashMap<>());
        } catch (Exception e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }
    }

    @Override
    public void update(Authentication entity) {
    }

    @Override
    public void delete(Long id) {
    }

    class AuthenticationRowMapper implements RowMapper<Authentication> {

        @Override
        public Authentication mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Authentication(rs.getLong("id"), rs.getTimestamp("created_at"), rs.getString("ip"), rs.getLong("user_id"));
        }
    }
}
