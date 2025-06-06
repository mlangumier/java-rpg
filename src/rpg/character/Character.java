package rpg.character;

public abstract class Character {
    protected String name;
    protected int maxHealth;
    protected int health;
    protected int defence;
    protected int attack;

    /**
     * Constructor
     * @param name
     * @param maxHealth
     * @param attack
     * @param defence
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
     * Check if the attack hits the other character.
     * @param target The character that is receiving the attack
     * @return Whether the attack hits the target or not
     */
    protected boolean attack(Character target) {
        //TODO: modify this setup -> add D20
        return this.getAttack() > target.getDefence();
    }

    /**
     * Record
     * @param damage
     * @return
     */
    protected void takeDamage(int damage) {
        // TODO: Finish setup -> add D6
//        this.getDefence() - damage;
    };

    /**
     * Checks if the character is still alive (still have positive "Health" stat)
     * @param character Character we're checking to see if they're alive
     * @return Wheter the character is still alive or not.
     */
    protected boolean isAlive(Character character) {
        return character.getHealth() > 0;
    }

    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", health=" + health +
                ", attack=" + attack +
                ", defence=" + defence +
                '}';
    }

}
