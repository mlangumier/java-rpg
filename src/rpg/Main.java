package rpg;

import rpg.character.enemy.creatures.Goblin;
import rpg.character.player.Player;
import rpg.enums.DiceType;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n========== JAVA RPG ==========");

        // TODO: Setup enemies/monsters
        // TODO: Start simple input
        // TODO: Setup combat manager
        // TODO: Continue input to trigger combat manager

        // ----- TESTING GROUND (START)

        Player player = new Player("Player", 30, 4, 17, 19, 2);
        Player enemy = new Player("Enemy", 35, 5, 14, 21, 1);
        testCombatPlayers(player, enemy);

        Player hero = new Player("Hero", 5, 2, 12, 30, 1);
        Goblin goblin = new Goblin();
        testCombatHeroVsGoblin(hero, goblin);

        testMiscellaneous();

        // ----- TESTING GROUND (END)

        /**
         * --- CREATE ENTITIES
         * Character:
         * - Player: has mana and can use powers (interface)
         * - Enemies: multiple types of enemies (each their own stats).
         * Combat manager: keeps track of turns & enemies felled (scores), has methods to manage combats & combatants.
         *
         * --- CREATE INTERFACES
         * Powers
         * (Dice -> types of dice?)
         *
         * --- TURN LOGIC
         * Player goes first & attacks -> Rolls a Dice20(+attack) = result attack
         * Check enemy damage (D#+#) & if still alive
         * If dead, combat won! Next enemy
         * Else, enemy attacks
         * Check player damages & if still alive
         * If dead, lost fight -> display score & record in file
         * Else, next turn.
         *
         * --- RULES (add to readme)
         * MaxHealth: maximum hit points. Character cannot have more Health than their MaxHealth.
         * Health: hit points. When Health <= 0, the character is dead.
         * Potion: number of potions available (only one sort at first), gives [insert calculus here] health back (ex: D&D minor potion)
         * Attacking = D20 + character.attack
         * Hit or not = Attacking > target's defence
         * Damage = D6 + character.attack -> substract that to the target's health (to make the character stronger, the number of D6 can then be upgraded to (1 * level)D6 + character.attack)
         * Mana = each spell requires mana, check if has enough first. ALSO: can use Mana to heal ONCE per combat.
         *
         * --- SCANNER (input)
         * 1. Player's name (string)
         * 2. Choice (int): Attack, Use Power, Use a Potion
         *
         * --- FILE READER
         * On character death:
         * - Gets data from file in an ordered list (by score DESC -- & LocalDate if same score)
         * - Insert the player's name, score and datetime to the correct order in the list
         * - Write the new list in the file
         * - Display the player's score & the full list
         *
         * --- EXCEPTIONS
         * Wrong input (show error & ask to input again)
         * "Not enough mana" (show error & do nothing)
         * "No potion available" (show error & do nothing)
         */
    }

    public static void testCombatPlayers(Player player, Player enemy) {
        System.out.println("\n---------- TEST COMBAT - PLAYER vs PLAYER ----------\n");

        try {
            int turnCounter = 1;

            while (player.checkIfCharacterIsAlive()) {
                System.out.printf("----- Turn %s -----%n", turnCounter++);
                if (turnCounter > 1) {
                    player.setMana(player.getMana() + 5);
                }

                // Forgetting to check potion > 0 on purpose
                if (player.getHealth() < player.getMaxHealth()) {
                    player.usePotion();
                }

                if (player.getMana() >= 20) {
                    player.spellAction(20, enemy);
                } else {
                    player.attackAction(enemy);
                }

                if (!enemy.checkIfCharacterIsAlive()) {
                    System.out.println("Enemy is dead!");
                    break;
                } else {
                    if ((enemy.getHealth() <= enemy.getMaxHealth() / 2) && (enemy.getPotion() > 0)) {
                        enemy.usePotion();
                    } else {
                        enemy.attackAction(player);
                    }
                }
            }

            System.out.printf("%nPlayer (alive:%s) -> %s %nEnemy (alive:%s) %s%n", player.checkIfCharacterIsAlive(), player, enemy.checkIfCharacterIsAlive(), enemy);
        } catch (Exception e) {
            System.err.printf("%nError: %s (from: %s)%n", e.getMessage(), e.getClass());
        }
    }

    public static void testCombatHeroVsGoblin(Player player, Goblin goblin) {
        System.out.println("\n---------- TEST COMBAT - HERO vs GOBLIN ----------");

        try {
            int counter = 1;
            while (player.checkIfCharacterIsAlive()) {
                System.out.printf("%n--- Turn %s ---%n", counter++);
                player.attackAction(goblin);

                if (!goblin.checkIfCharacterIsAlive()) {
                    System.out.println("Goblin is dead!");
                    break;
                } else {
                    goblin.attackAction(player);
                }

                System.out.println("Hero is dead!");
            }

            System.out.printf("%nHero (alive:%s) -> %s %nGoblin (alive:%s) %s%n", player.checkIfCharacterIsAlive(), player, goblin.checkIfCharacterIsAlive(), goblin);
        } catch (Exception e) {
            System.err.printf("%nError: %s (from: %s)%n", e.getMessage(), e.getClass());
        }
    }

    public static void testMiscellaneous() {
        System.out.println("\n---------- TEST MISCELLANEOUS ----------\n");

        System.out.printf("D20: Enum=%s -> value=%s %n", DiceType.TWENTY, DiceType.TWENTY.getValue());
    }
}