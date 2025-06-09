package rpg;

import rpg.character.enemy.Enemy;
import rpg.character.enemy.creatures.Goblin;
import rpg.character.player.Player;
import rpg.enums.ActionType;
import rpg.services.GameManager;
import rpg.utils.Action;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameManager gameManager = new GameManager();
        Action action = new Action();
        Player player;
        Enemy enemy; // (default) Easy first enemy

        System.out.println("\n========= JAVA RPG ==========\n");

        // ----- CHARACTER CREATION
        // TODO: manage error for wrong input (empty and perhaps regex other than [-'])
        System.out.print("Enter a name for your character: ");
        String characterName = scanner.nextLine();

        player = gameManager.createPlayerCharacter(characterName);
        System.out.printf("Your character: %s%n%n", player.getInfo());

        // ----- GAME BEGINS
        while (player.checkIfCharacterIsAlive()) {
            // Init combat
            enemy = gameManager.createNewEnemy();

            // TODO: MANAGE THIS FIRST !!!
            // TODO: method to manage a combat (1 player vs 1 enemy -> return the character who's alive)
            // -> So once a combat is over, back into WHILE_LOOP & create another enemy + add kill to counter
            System.out.println(player.getInfo());
            System.out.printf("Actions: %s %nChoose an action: ", action.displayActionChoices());
            int selectedAction = scanner.nextInt();

            // TODO: manage errors for non-int & non-valid values
            switch (action.getActionType(selectedAction)) {
                case ActionType.ATTACK -> player.attackAction(enemy);
                case ActionType.CAST_SPELL -> player.spellAction(enemy);
                case ActionType.DRINK_POTION -> player.usePotion();
                case ActionType.LEAVE_GAME -> scanner.close();
                default -> System.out.println("Please select a a valid action");
            }

            if (enemy.checkIfCharacterIsAlive()) {
                System.out.println("(temp) Enemy plays its turn");
            } else {
                System.out.printf("%s is dead!", enemy.getName());
            }

            // Check if this is read by the console
            if (!player.checkIfCharacterIsAlive()) {
                System.out.printf("----- Oh no! %s was killed by %s! -----", player.getName(), enemy.getName());
            }
        }


        scanner.close();

        /**
         * --- TODO:
         * - [x] Refactor `Dice` to work with Enum
         * - [x] Create other types of enemies (ex: Goblin leader, wolf, troll, dragon)
         * - [x] Being Input (Scanner) setup & tests
         * - [] Test Exceptions & rework implementation if need be
         * - [] CombatManager or EnemyService to manage a list of enemies (?)
         *
         * --- CREATE ENTITIES
         * Character:
         * - [x] Player: has mana and can use powers (interface)
         * - [] Enemies: multiple types of enemies (each their own stats).
         * - [] Combat manager: keeps track of turns & enemies felled (scores), has methods to manage combats & combatants.
         *
         * --- CREATE INTERFACES
         * - [x] Powers
         * - [x] (Dice -> types of dice?)
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
         * Damage = D6 + character.attack -> subtract that to the target's health (to make the character stronger, the number of D6 can then be upgraded to (1 * level)D6 + character.attack)
         * Mana = each spell requires mana, check if has enough first. ALSO: can use Mana to heal ONCE per combat.
         *
         * --- SCANNER (input)
         * - [] Player's name (string)
         * - [] Choice (int): Attack, Use Power, Use a Potion
         * - [] On input error, show error message & show choices again + ask input again
         *
         * --- FILE READER
         * On character death:
         * - [] Display "You died" message
         * - [] Gets data from file in an ordered list (by score DESC -- & LocalDate if same score)
         * - [] Insert the player's name, score and datetime to the correct order in the list
         * - [] Write the new list in the file
         * - [] Display the player's score & the full list
         *
         * --- EXCEPTIONS
         * - [] Wrong input (show error & ask to input again) -> Player Name & Action choice
         * - [x] "Not enough mana" (show error & do nothing)
         * - [x] "No potion available" (show error & do nothing)
         */
    }

    public static void testCombatPlayers(Player player, Player enemy) {
        System.out.println("\n---------- TEST COMBAT - PLAYER vs PLAYER ----------\n");

        try {
            int turnCounter = 1;

            while (player.checkIfCharacterIsAlive()) {
                System.out.printf("----- Turn %s -----%n", turnCounter++);
                System.out.printf("%s: %s/%s HP, %s mana, %s potions %n", player.getName(), player.getHealth(), player.getMaxHealth(), player.getMana(), player.getPotion());
                if (turnCounter > 1) {
                    player.setMana(player.getMana() + 5);
                }

                // Forgetting to check potion > 0 on purpose
                if (player.getHealth() < player.getMaxHealth()) {
                    player.usePotion();
                }

                if (player.getMana() >= 20) {
                    player.spellAction(enemy);
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
            }

            System.out.printf("%n%s %n%s%n", player, goblin);
        } catch (Exception e) {
            System.err.printf("%nError: %s (from: %s)%n", e.getMessage(), e.getClass());
        }
    }
}