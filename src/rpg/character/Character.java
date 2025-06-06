package rpg.character;

import rpg.utils.Dice;

public abstract class Character {
    protected String name;
    protected int maxHealth;
    protected int health;
    protected int defence;
    protected int attack;

    /**
     * Constructor
     * @param name Name of the character
     * @param maxHealth Maximum health of the character (can not go above), also sets the character's starting health
     * @param attack The bonus the character will add to their attack to hit other characters
     * @param defence The protection score of the character, given through armor or evasion.
     */
    protected Character(String name, int maxHealth, int attack, int defence) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.attack = attack;
        this.defence = defence;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    /**
     * Calculates the score of the attack
     * @return The score of the attack the character is making.
     */
    protected int calcAttack() {
        return new Dice(20).rollDice() + this.getAttack();
    }

    /**
     * Checks whether the attack hits the target or not
     * @param attack Score of the attack that the target is receiving
     * @return Whether the attack hits the target or not
     */
    protected boolean doesAttackHit(int attack, Character target) {
        return attack >= target.getDefence();
    }

    /**
     * Calculates the damage the character does with his attack
     * @return The amount of damage the character will inflict
     */
    protected int calcDamage() {
        return this.attack + new Dice(6).rollDice();
    }

    /**
     * Records the damage taken by the character.
     * @param damage The amount of damage the character is taking
     */
    protected void inflictDamage(int damage) {
        this.health -= damage;
    }

    /**
     * Checks if the character is still alive ("health" above "0")
     * @param character Character we're checking
     * @return Whether the character is still alive or not.
     */
    protected boolean isCharacterAlive(Character character) {
        return character.getHealth() > 0;
    }

    /**
     * Displays the character's health compared to their max health
     * @return A String showing how much health the character has compared to their max health
     */
    protected String showRemainingHealth () {
        return String.format("%s/%s", this.health, this.maxHealth);
    }


    @Override
    public String toString() {
        return String.format("Character: {name='%s', health=%s/%s, attack_bonus=%s, defence=%s}", name, health, maxHealth, attack, defence);
    }

}
