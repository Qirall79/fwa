package fr.fortytwo.cinema.repositories;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;

@Component
public class UsersRepositoryImpl implements UsersRepository {
    private DataSource dataSource;

    public UsersRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
