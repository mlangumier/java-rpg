package rpg.character.enemy.creatures;

import rpg.character.enemy.Enemy;

/**
 * Goblins are monster that are generally easy to kill
 */
public class Goblin extends Enemy {
    /**
     * Default Constructor
     */
    public Goblin() {
        super();
        this.setName("Goblin");
        this.setHealth(7);  // 2d6
        this.setAttack(this.generateAttackStat(4, 1)); // +1/+4
        this.setDefence(13); // 8 + 2d4
    }
}
