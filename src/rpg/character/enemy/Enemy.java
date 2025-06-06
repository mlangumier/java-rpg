package rpg.character.enemy;

import rpg.character.Character;
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

    // ----- Generate stats

    /**
     * Generate the enemy's { attack } stat randomly by rolling a dice {hitDie} multiple times {numberOfDice}
     * @param hitDie Which dice to use to roll (d6, d8, d12, etc.)
     * @param numberOfDice How many dice do we roll
     * @return The attack bonus score of the enemy
     */
    public int generateAttackStat(int hitDie, int numberOfDice) {
        return new Dice(hitDie).rollMultipleDice(numberOfDice);
    }
}
