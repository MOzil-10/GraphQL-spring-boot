package graphQL.Java.graphQL.contoller;

import graphQL.Java.graphQL.model.Player;
import graphQL.Java.graphQL.model.Team;
import graphQL.Java.graphQL.service.PlayerService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;


@Controller
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @QueryMapping
    public List<Player> findAll() {
      return playerService.findAllPlayers();
    }

    @QueryMapping
    public Optional<Player> findOne(@Argument Integer id) {
        return playerService.findPlayerById(id);
    }

    @MutationMapping
    public Player createPlayer(@Argument String name,@Argument Team team) {
        return playerService.createPlayer(name,team);
    }
}
