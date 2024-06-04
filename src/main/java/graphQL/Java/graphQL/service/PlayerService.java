package graphQL.Java.graphQL.service;

import graphQL.Java.graphQL.model.Player;
import graphQL.Java.graphQL.model.Team;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PlayerService {

    private List<Player> players = new ArrayList<>();

    AtomicInteger id = new AtomicInteger(0);

    public List<Player> findAllPlayers() {
        return players;
    }

    public Optional<Player> findPlayerById(Integer id) {
        return players.stream()
                .filter(player -> player.Id() == id).findFirst();
    }

    public Player createPlayer(String name, Team team) {
        Player player = new Player(id.incrementAndGet(),name,team);
        players.add(player);
        return player;
    }

    public Player deletePlayer(Integer id) {
        Player player = players.stream().filter(c -> c.Id() == id)
                .findFirst().orElseThrow(() -> new IllegalArgumentException());
        players.remove(player);
        return player;
    }

    public Player updatePlayer(Integer id, String name, Team team) {

        Player updatedPlayer = new Player(id,name,team);
        Optional<Player> optional = players.stream()
                .filter(c->c.Id() == id).findFirst();

        if(optional.isPresent()) {
            Player player = optional.get();
            int index = players.indexOf(player);
            players.set(index,updatedPlayer);
        }
        else {
            throw new IllegalArgumentException("Invalid player");
        }
        return updatedPlayer;
    }

    @PostConstruct
    private void init() {
        players.add(new Player(id.incrementAndGet(),"Ozil",Team.ARSENAL));
        players.add(new Player(id.incrementAndGet(),"Ronaldo",Team.CHELSEA));
        players.add(new Player(id.incrementAndGet(),"Messi",Team.MANU));
        players.add(new Player(id.incrementAndGet(),"Vini",Team.MANCITY));
    }
}
