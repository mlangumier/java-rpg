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

    // TODO: Method attack(Character target)

    // TODO: Method calcDamageTaken(int damage)

    // TODO: Method isAlive()

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
