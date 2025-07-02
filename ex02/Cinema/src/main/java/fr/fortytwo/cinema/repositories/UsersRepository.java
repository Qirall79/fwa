package fr.fortytwo.cinema.repositories;

import java.util.Optional;

import fr.fortytwo.cinema.models.User;

public interface UsersRepository extends CrudRepository<User> {
    Optional<User> findByPhoneNumber(String phoneNumber);
}
