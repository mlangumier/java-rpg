package rpg.character.player;

import rpg.character.Character;
import rpg.utils.Dice;

public class Player extends Character {
    private int mana;
    private int maxMana;
    private int potion;

    /**
     * Constructor
     *
     * @param name      Name of the character
     * @param maxHealth Maximum health of the character, also sets the character's starting health (can not go above max)
     * @param attack    The bonus the character will add to their attack to hit other characters
     * @param defence   The protection score of the character, given through armor or evasion
     * @param maxMana   The maximum mana resource the character will spend to use their powers, also sets the character's starting mana (can not go above max)
     * @param potion    The number of potions the character can use to regain health
     */
    protected Player(String name, int maxHealth, int attack, int defence, int maxMana, int potion) {
        super(name, maxHealth, attack, defence);
        this.maxMana = maxMana;
        this.mana = maxMana;
        this.potion = potion;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public int getPotion() {
        return potion;
    }

    public void setPotion(int potion) {
        this.potion = potion;
    }

    // TODO: Setup Exceptions & add class THROWS exceptions
    private void usePotion() {
        if (this.potion <= 0) { return; } // TODO Exception - If no more potions, can't use it
        if (this.getHealth() == this.getMaxHealth()) { return; } // TODO Exception - If health is full, can't use a potion

        int roll = new Dice(4).rollDice();

        try {
            this.setHealth(this.getHealth() + roll);

            if (this.getHealth() > maxHealth) {
                setHealth(maxHealth);
            }

            System.out.printf("Healed %shp -> %s/%s", roll, this.getHealth(), this.getMaxHealth());
        } catch (Exception e) {
            System.err.printf("Error: %s (from: %s)%n", e.getMessage(), e.getClass());
        }
    }

    // Method usePower() // from interface (implement)

    @Override
    public String toString() {
        return String.format("Character: {name='%s', health=%s/%s, mana=%s/%s, attack_bonus=%s, defence=%s, nbr_of_potions=%s}", name, health, maxHealth, mana, maxMana, attack, defence, potion);
    }
}
