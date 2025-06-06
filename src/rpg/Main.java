package rpg;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome!\nEverything is fine.");

        /**
         * --- CREATE ENTITIES
         * Character:
         * - Player: has mana and can use powers (interface)
         * - Enemies: multiple types of enemies
         * Combat manager: keeps track of turns & enemies felled (scores), has methods to manage combats & combatants.
         *
         * --- CREATE INTERFACES
         * Powers
         *
         * --- TURN LOGIC
         * Player goes first & attacks
         * Check enemy damages & if still alive
         * If dead, combat won! Next enemy
         * Else, enemy attacks
         * Check player damages & if still alive
         * If dead, lost fight -> display score & record in file
         * Else, next turn.
         */
    }
}