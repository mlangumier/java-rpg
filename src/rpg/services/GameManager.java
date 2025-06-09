package rpg.services;

import rpg.character.enemy.Enemy;
import rpg.character.enemy.creatures.Goblin;
import rpg.character.enemy.creatures.GoblinLeader;
import rpg.character.enemy.creatures.Troll;
import rpg.character.enemy.creatures.Wolf;
import rpg.character.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameManager {
    Random random = new Random();
    List<Enemy> enemies = new ArrayList<>();

    public GameManager() {
        enemies.add(new Wolf());
        enemies.add(new Goblin());
        enemies.add(new GoblinLeader());
        enemies.add(new Troll());
    }

    /**
     *
     * @param name
     * @return
     */
    public Player createPlayerCharacter (String name) {
        return new Player(name, 50, 5, 15, 30, 1);
    }

    /**
     *
     * @return
     */
    public Enemy createNewEnemy () {
        // Bonus: % of chance to be selected depends on enemy difficulty)
        return this.enemies.get(random.nextInt(enemies.size()));
    }
}
