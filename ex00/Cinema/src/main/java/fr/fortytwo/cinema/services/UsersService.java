package fr.fortytwo.cinema.services;

import org.springframework.stereotype.Component;

import fr.fortytwo.cinema.repositories.UsersRepository;

@Component
public class UsersService {
    
    private UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public String getUsername() {
        return "Jilali";
    }
}
