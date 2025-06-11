package rpg.character.hero;

import rpg.character.Character;
import rpg.enums.DiceType;
import rpg.exceptions.ItemNotAvailable;
import rpg.exceptions.MissingItemException;
import rpg.exceptions.NotEnoughResourceException;
import rpg.exceptions.ResourceFullException;
import rpg.interfaces.Spell;
import rpg.utils.Dice;

public class Hero extends Character implements Spell {
    private int mana;
    private int maxMana;
    private int potions;
    private boolean isPotionAlreadyUsed;

    /**
     * Constructor
     *
     * @param name      Name of the character
     * @param maxHealth Maximum health of the character, also sets the character's starting health (can not go above max)
     * @param attack    The bonus the character will add to their attack to hit other characters
     * @param defence   The protection score of the character, given through armor or evasion
     * @param maxMana   The maximum mana resource the character will spend to use their powers, also sets the character's starting mana (can not go above max)
     * @param potions   The number of potions the character can use to regain health
     */
    public Hero(String name, int maxHealth, int attack, int defence, int maxMana, int potions) {
        super(name, maxHealth, attack, defence);
        this.maxMana = maxMana;
        this.mana = maxMana;
//        this.maxPotions = 3;
        this.potions = potions;
        this.isPotionAlreadyUsed = false;
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

    public int getPotions() {
        return potions;
    }

    public void setPotions(int potions) {
        this.potions = potions;
    }

    public boolean getIsPotionAlreadyUsed() {
        return isPotionAlreadyUsed;
    }

    public void setIsPotionAlreadyUsed(boolean potionAlreadyUsed) {
        isPotionAlreadyUsed = potionAlreadyUsed;
    }

    /**
     * The hero uses a Potion to heal himself and regain some Health
     */
    public void usePotion() throws MissingItemException, ResourceFullException {
        this.checkPotionAvailability();
        this.checkIsHealthFull();
        this.checkPotionAlreadyUsed();

        int formerHealth = getHealth();
        int roll = new Dice(DiceType.SIX).rollMultipleDice(2) + 10; // Heals for ~17hp (12-22) -> automate later
        this.setPotions(this.getPotions() - 1);
        this.setIsPotionAlreadyUsed(true);
        this.setHealth(this.getHealth() + roll);

        if (this.getHealth() > this.getMaxHealth()) {
            setHealth(this.getMaxHealth());
        }

        System.out.printf("Healed %shp (%shp -> %shp)%n", roll, formerHealth, this.showRemainingHealth());
    }

    /**
     * (temp) Action casting a SPELL - regroup the actions a character makes when casting a spell.
     * A spell never misses. Calculates the damage done to the enemy.
     *
     * @param target The target of the spell
     */
    public void spellAction(Character target) throws NotEnoughResourceException {
        int manaCost = 20;

        this.checkManaAvailability(manaCost);
        this.setMana(getMana() - manaCost);

        int damage = useSpell(manaCost);

        target.receivesDamage(damage);

        System.out.printf("%s used a powerful spell against %s and dealt %s damage.%n", this.getName(), target.getName(), damage);
    }

    /**
     * The hero casts a powerful spell that cannot miss but consumes some of his mana
     * [Spell damage = attack + (attack)D8 (ex: 4 + 4D8)]
     *
     * @param manaCost the amount of mana required to cast the spell
     * @return The amount of damage the spell is going to inflict
     */
    @Override
    public int useSpell(int manaCost) {
        int damage = this.getAttack();
        for (int i = this.getAttack(); i > 0; i--) {
            damage += new Dice(DiceType.EIGHT).rollDice();
        }
        return damage;
    }

    /**
     * Gives the player a certain amount of Mana back
     *
     * @param quantity How much mana the player is regaining
     * @throws ResourceFullException if the player is already at full mana capacity.
     */
    public void regainMana(int quantity) throws ResourceFullException {
        this.checkIsManaFull();
        int tempMana = this.getMana() + quantity;

        if (tempMana >= this.getMaxMana()) {
            this.setMana(this.getMaxMana());
        } else {
            this.setMana(this.getMana() + quantity);
        }
    }

    // --- EXCEPTIONS CHECKERS

    /**
     * Checks if potions are available
     *
     * @throws MissingItemException if no potions are available
     */
    private void checkPotionAvailability() throws MissingItemException {
        if (this.getPotions() <= 0) {
            throw new MissingItemException("You don't have any potion to use!");
        }
    }

    /**
     * Check if the user has already used a potion during this fight
     *
     * @throws ItemNotAvailable if the user has already used a potion during the fight.
     */
    private void checkPotionAlreadyUsed() throws ItemNotAvailable {
        if (this.getIsPotionAlreadyUsed()) {
            throw new ItemNotAvailable("You cannot drink any more potion during this fight.");
        }
    }

    /**
     * Check if the hero's health is already full
     *
     * @throws ResourceFullException if the hero's health is already full
     */
    private void checkIsHealthFull() throws ResourceFullException {
        if (this.getHealth() == this.getMaxHealth()) {
            throw new ResourceFullException("Your health is already full!");
        }
    }

    /**
     * Check if the user's mana is currently full.
     *
     * @throws ResourceFullException if the user's current mana is already full.
     */
    private void checkIsManaFull() throws ResourceFullException {
        if (this.getMana() >= this.getMaxMana()) {
            throw new ResourceFullException("Mana is full. Can't regain any more mana.");
        }
    }

    /**
     * Check if the hero has enough mana to cast the spell
     *
     * @param manaCost The amount of mana the hero needs to cast the spell
     * @throws NotEnoughResourceException if the hero doesn't have enough mana to cast the spell
     */
    private void checkManaAvailability(int manaCost) throws NotEnoughResourceException {
        if (manaCost > this.getMana()) {
            throw new NotEnoughResourceException("You don't have enough mana to cast that spell!");
        }
    }
    // --- DISPLAY METHODS

    /**
     * Gets information the player should know about his hero
     *
     * @return Information about the hero: name, health, defence mana, number of potions
     */
    public String getInfo() {
        return String.format(
                "%s: %s/%s HP | %s Defence | %s/%s Mana | %s Potion%s",
                this.getName(), this.getHealth(), this.getMaxHealth(), this.getDefence(), this.getMana(), this.getMaxMana(), this.getPotions(), this.getPotions() > 1 ? "s" : ""
        );
    }

    @Override
    public String toString() {
        return String.format(
                "hero: {name=%s, health=%s/%s, mana=%s/%s, attack_bonus=%s, defence=%s, nbr_of_potions=%s}",
                name, health, maxHealth, mana, maxMana, attack, defence, potions
        );
    }
}
