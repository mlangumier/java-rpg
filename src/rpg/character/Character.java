package rpg.character;

public abstract class Character {
    protected String name;
    protected int health;
    protected int attack;
    protected int defence;

    /**
     * Constructor
     * @param name
     * @param health
     * @param attack
     * @param defence
     */
    protected Character(String name, int health, int attack, int defence) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defence = defence;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
     * Check if the attack hits the other character
     * @param target The character that is receiving the attack
     * @return Whether the attack hits the target or not
     */
    protected boolean attack(Character target) {
        return this.getAttack() > target.getDefence();
    }

    // TODO: Method calcDamageTaken(int damage)
    protected int calDamageTaken(int damage) {
        return this.health;
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
