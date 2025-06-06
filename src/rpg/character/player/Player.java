package rpg.character.player;

import rpg.character.Character;
import rpg.exceptions.MissingItemException;
import rpg.exceptions.NotEnoughResourceException;
import rpg.exceptions.ResourceFullException;
import rpg.interfaces.Spell;
import rpg.utils.Dice;

public class Player extends Character implements Spell {
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
    public Player(String name, int maxHealth, int attack, int defence, int maxMana, int potion) {
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

    /**
     * The player uses a Potion to heal himself and regain some Health
     */
    public void usePotion() {
        try {
            this.checkPotionAvailability();
            this.checkIsHealthFull();

            int formerHealth = getHealth();
            int roll = new Dice(4).rollDice() + 4;
            this.setHealth(this.getHealth() + roll);

            if (this.getHealth() > this.getMaxHealth()) {
                setHealth(this.getMaxHealth());
            }

            System.out.printf("Healed %shp (%s/%s) -> %s)%n", roll, formerHealth, this.getMaxHealth(), this.showRemainingHealth());
        } catch (MissingItemException | ResourceFullException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.err.printf("Error: %s (from: %s)%n", e.getMessage(), e.getClass());
        }
    }

    @Override
    public void useSpell() {
        int manaCost = 10; // Default placeholder
        try {
            this.checkManaAvailability(manaCost);

            // TODO: Rework character to the attack ca be done with one function as well (calc, hits, dmg) & show log
            // TODO: Implement spell-attack doing (attack)D8 dmg

        } catch (NotEnoughResourceException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.err.printf("Error: %s (from: %s)%n", e.getMessage(), e.getClass());
        }
    }

    // --- EXCEPTIONS CHECKERS

    /**
     * Checks if potions are available
     * @throws MissingItemException if no potions are available
     */
    private void checkPotionAvailability() throws MissingItemException {
        if (this.getPotion() <= 0) {
            throw new MissingItemException("You don't have any potion to use!");
        }
    }

    /**
     * Check if the player's health is already full
     * @throws ResourceFullException if the player's health is already full
     */
    private void checkIsHealthFull() throws ResourceFullException {
        if (this.getHealth() == this.getMaxHealth()) {
            throw new ResourceFullException("Your health is already full!");
        }
    }

    /**
     * Check if the player has enough mana to cast the spell
     * @param manaCost The amount of mana the player needs to cast the spell
     * @throws NotEnoughResourceException if the player doesn't have enough mana to cast the spell
     */
    private void checkManaAvailability(int manaCost) throws NotEnoughResourceException {
        if (manaCost > this.getMana()) {
            throw new NotEnoughResourceException("You don't have enough mana!");
        }
    }

    @Override
    public String toString() {
        return String.format("Player: {name=%s, health=%s/%s, mana=%s/%s, attack_bonus=%s, defence=%s, nbr_of_potions=%s}", name, health, maxHealth, mana, maxMana, attack, defence, potion);
    }

}
