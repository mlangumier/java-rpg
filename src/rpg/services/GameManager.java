package rpg.services;

import rpg.character.enemy.Enemy;
import rpg.character.enemy.creatures.*;
import rpg.character.hero.Hero;
import rpg.enums.EnemyType;

import java.util.Random;

public class GameManager {
    Random random = new Random();

    public GameManager() {
        // Document why this constructor is empty (sonarqube)
    }

    /**
     * Create a new character named by the player.
     *
     * @param name Name the player gives to his Hero.
     * @return A new character.
     */
    public Hero createHero(String name) {
        return new Hero(name, 50, 5, 15, 40, 3);
    }

    /**
     * Create a random enemy from the different types available.
     * (suggestion for later: update the random method & switch to work in percentage & define "rarity" for enemies so easier enemies appear more often that strong enemies.
     *
     * @return A new enemy.
     */
    public Enemy createNewEnemy() {
        EnemyType randomEnemy = EnemyType.values()[random.nextInt(EnemyType.values().length)];

        return switch (randomEnemy) {
            case EnemyType.GOBLIN -> new Goblin();
            case EnemyType.GOBLIN_LEADER -> new GoblinLeader();
            case EnemyType.TROLL -> new Troll();
            case EnemyType.WOLF -> new Wolf();
            case EnemyType.BEAR -> new Bear();
            case EnemyType.DRAGON -> new Dragon();
        };
    }

    /**
     * Displays the actions the player can take during his turn.
     *
     * @return A string that displays the actions the player can take.
     */
    public String displayActionChoices() {
        return "1. Attack | 2. Cast Spell | 3. Drink Potion | 4. Exit Game";
    }
}
