package rpg;

import rpg.character.player.Player;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n========== JAVA RPG ==========");

        // TODO: Start simple input
        // TODO: Setup combat manager
        // TODO: Continue input to trigger combat manager

        // ----- TESTING GROUND (START)

        Player player = new Player("Matt", 30, 4, 12, 19, 2);
        Player enemy = new Player("Sam", 20, 5, 14, 21, 0);

        try {
            System.out.println("\n----- Matt's turn -----");
            player.usePotion();
            player.attackAction(enemy);
            player.spellAction(20, enemy);

            if (enemy.checkIfCharacterIsAlive()) {
                System.out.println("\n----- Sam's turn -----");
                enemy.usePotion();
                enemy.attackAction(player);
                enemy.spellAction(20, player);


                if (player.checkIfCharacterIsAlive()) {
                    System.out.println("\n----- Matt's turn -----");
                    player.usePotion();
                    player.setMana(player.getMana() + 5); // Add mana on turn 2 to cast spell
                    player.spellAction(20, enemy);
                    player.attackAction(enemy);
                } else {
                    System.out.println("Player is dead!");
                }
            } else {
                System.out.println("Enemy is dead!");
            }


            // Check both:
            System.out.printf("%nPlayer (alive:%s) -> %s %nEnemy (alive:%s) %s%n", player.checkIfCharacterIsAlive(), player, enemy.checkIfCharacterIsAlive(), enemy);
        } catch (Exception e) {
            System.err.printf("%nError: %s (from: %s)%n", e.getMessage(), e.getClass());
        }

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
}