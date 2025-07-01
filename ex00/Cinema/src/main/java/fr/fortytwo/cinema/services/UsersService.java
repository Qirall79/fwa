package fr.fortytwo.cinema.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import fr.fortytwo.cinema.models.User;
import fr.fortytwo.cinema.repositories.UsersRepository;

@Component
public class UsersService {

    private UsersRepository usersRepository;
    private PasswordEncoder passwordEncoder;

    public UsersService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(User user) {
        // hash password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        usersRepository.save(user);
    }

    public User signInUser(String phoneNumber, String password) {
        User user = usersRepository.findByPhoneNumber(phoneNumber).orElse(null);

        if (user != null) {
            if (!passwordEncoder.matches(password, user.getPassword())) {
                return null;
            }
        }

        return user;
    }

    public String getUsername() {
        return "Jilali";
    }
}
