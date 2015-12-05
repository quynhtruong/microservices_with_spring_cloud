package de.thtesche.udemy;

import de.thtesche.udemy.dao.TeamDao;
import de.thtesche.udemy.domain.Player;
import de.thtesche.udemy.domain.Team;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroservicesBootApplication extends SpringBootServletInitializer {

  @Autowired
  TeamDao teamDao;

  /**
   * Used when run as a JAR
   *
   * @param args
   */
  public static void main(String[] args) {
    SpringApplication.run(MicroservicesBootApplication.class, args);
  }

  @PostConstruct
  public void init() {
    Set<Player> players = new HashSet<>();
    players.add(new Player("Player 1", "Position 1"));
    players.add(new Player("Player 3", "Position 3"));

    Team team = new Team("A-Team", "Berlin", players);
    teamDao.save(team);
  }

}
