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

import fr.fortytwo.cinema.models.Avatar;
import fr.fortytwo.cinema.models.User;

@Component
public class AvatarsRepositoryImpl extends NamedParameterJdbcTemplate implements AvatarsRepository {

    private DataSource dataSource;

    public AvatarsRepositoryImpl(DataSource dataSource) {
        super(dataSource);
        this.dataSource = dataSource;
    }

    @Override
    public Avatar findById(Long id) {
        return null;
    }

    @Override
    public List<Avatar> findAll() {
        List<Avatar> avatars = query("SELECT * FROM avatars", new HashMap<>(), new AvatarRowMapper());

        return avatars;
    }
    
    @Override
    public List<Avatar> findByUserId(Long id) {
        List<Avatar> avatars = query("SELECT * FROM avatars WHERE user_id = %d".formatted(id), new HashMap<>(), new AvatarRowMapper());

        return avatars;
    }

    

    @Override
    public void save(Avatar entity) {
        try {
            update("INSERT INTO avatars (file_size, file_name, mime_type, user_id) VALUES (%d, '%s', '%s', %d)".formatted(entity.getFileSize(), entity.getFileName(), entity.getMimeType(), entity.getUserId()), new HashMap<>());
        } catch (Exception e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }
    }

    @Override
    public void update(Avatar entity) {
    }

    @Override
    public void delete(Long id) {
    }

    class AvatarRowMapper implements RowMapper<Avatar> {

        @Override
        public Avatar mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Avatar(rs.getLong("id"), rs.getLong("file_size"), rs.getString("file_name"), rs.getString("mime_type"), rs.getLong("user_id"));
        }
    }
}
