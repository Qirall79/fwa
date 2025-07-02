package fr.fortytwo.cinema.repositories;

import java.util.List;
import java.util.Optional;

import fr.fortytwo.cinema.models.Avatar;

public interface AvatarsRepository extends CrudRepository<Avatar> {
    List<Avatar> findByUserId(Long id);
}
