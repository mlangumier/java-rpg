package rpg.character.enemy;

import rpg.character.Character;
import rpg.enums.DiceType;
import rpg.utils.Dice;

public abstract class Enemy extends Character {

    /**
     * Default Constructor
     */
    protected Enemy() {
        super();
    }

    /**
     * Constructor
     *
     * @param name      Name of the character
     * @param maxHealth Maximum health of the character (can not go above), also sets the character's starting health
     * @param attack    The bonus the character will add to their attack to hit other characters
     * @param defence   The protection score of the character, given through armor or evasion.
     */
    protected Enemy(String name, int maxHealth, int attack, int defence) {
        super(name, maxHealth, attack, defence);
    }

    // Setup methods to generate semi-random stats,  (ex: goblin has 2d6 health)

    public String getInfo () {
        return String.format("%s: %s HP",  this.getName(), this.getHealth());
    }

    @Override
    public String toString() {
        return String.format("Enemy: {name=%s, health=%s/%s, attack_bonus=%s, defence=%s}", name, health, maxHealth, attack, defence);
    }
}
