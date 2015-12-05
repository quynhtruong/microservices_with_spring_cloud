package de.thtesche.udemy.dao;

import de.thtesche.udemy.domain.Player;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author thtesche
 */
public interface PlayerDao extends CrudRepository<Player, Long> {

  List<Player> findAll();

  Player findByName(String name);

}
