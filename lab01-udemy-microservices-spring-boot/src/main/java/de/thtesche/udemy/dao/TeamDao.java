package de.thtesche.udemy.dao;

import de.thtesche.udemy.domain.Team;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author thtesche
 */
public interface TeamDao extends CrudRepository<Team, Long> {

  @Override
  List<Team> findAll();

  Team findByName(String name);

  @Override
  public <S extends Team> S save(S entity);

  @Override
  public void delete(Team entity);

}
