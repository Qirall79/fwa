package fr.fortytwo.cinema.repositories;

import java.util.List;
import java.util.Optional;

import fr.fortytwo.cinema.models.Authentication;

public interface AuthenticationsRepository extends CrudRepository<Authentication> {
    List<Authentication> findByUserId(Long id);
}
