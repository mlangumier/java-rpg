package rpg.character;

import rpg.enums.DiceType;
import rpg.utils.Dice;

public abstract class Character {
    protected String name;
    protected int maxHealth;
    protected int health;
    protected int defence;
    protected int attack;

    /**
     * Default Constructor
     */
    protected Character() {}

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
     * (temp) Action ATTACK - regroups the actions a character makes when attacking.
     * Calculates the attack score, checks if the attack hits the enemy and how much damage is dealt.
     * (Uses other methods)
     * @param target The target of the attack
     */
    public void attackAction(Character target) {
        // TODO: full combat method (temp -> move to a CombatManager/ActionManager (PlayerService?) later)

        int attackScore = this.calcAttack();
        boolean doesAttackHits = checkIfAttackHits(attackScore, target);

        if (doesAttackHits) {
            int damage = this.calcDamage();
            target.receivesDamage(damage);

            System.out.printf("%s successfully attacked %s (%s) and dealt %s damage.%n", this.getName(), target.getName(), attackScore, damage);
        } else {
            System.out.printf("%s tried to attack %s but missed!%n", this.getName(), target.getName());
        }
    }

    /**
     * Calculates the score of the attack
     * @return The score of the attack the character is making.
     */
    public int calcAttack() {
        return new Dice(DiceType.TWENTY).rollDice() + this.getAttack();
    }

    /**
     * Checks whether the attack hits the target or not
     * @param attack Score of the attack that the target is receiving
     * @return Whether the attack hits the target or not
     */
    public boolean checkIfAttackHits(int attack, Character target) {
        return attack >= target.getDefence();
    }

    /**
     * Calculates the damage the character does with his attack
     * @return The amount of damage the character will inflict
     */
    public int calcDamage() {
        return this.getAttack() + new Dice(DiceType.SIX).rollDice();
    }

    /**
     * Records the damage taken by the character.
     * @param damage The amount of damage the character is taking
     */
    public void receivesDamage(int damage) {
        this.setHealth(Math.max(this.getHealth() - damage, 0)); //Math.max(a,b) returns the highest: if damage drops the character bellow 0hp, set health to 0, else set the remaining health.
    }

    /**
     * Checks if the character is still alive ("health" above "0")
     * @return Whether the character is still alive or not.
     */
    public boolean checkIfCharacterIsAlive() {
        return this.getHealth() > 0;
    }

    /**
     * Displays the character's health compared to their max health
     * @return A String showing how much health the character has compared to their max health
     */
    public String showRemainingHealth () {
        return String.format("%s/%s", this.health, this.maxHealth);
    }


    @Override
    public String toString() {
        return String.format("Character: {name=%s, health=%s/%s, attack_bonus=%s, defence=%s}", name, health, maxHealth, attack, defence);
    }
}
