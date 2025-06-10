package rpg;

import rpg.character.enemy.Enemy;
import rpg.character.player.Player;
import rpg.enums.ActionType;
import rpg.exceptions.MissingItemException;
import rpg.exceptions.NotEnoughResourceException;
import rpg.exceptions.ResourceFullException;
import rpg.exceptions.WrongUserInput;
import rpg.services.GameManager;
import rpg.services.ScannerManager;

public class Main {
    public static void main(String[] args) {
        ScannerManager scanner = new ScannerManager();
        GameManager gameManager = new GameManager();

        Player player;
        Enemy enemy;
        int combatCounter = 0;
        int enemiesKilled = 0;

        System.out.println("\n========= JAVA RPG ==========\n");

        // ----- CHARACTER CREATION
        String characterName = "";
        while (characterName.isEmpty()) {
            try {
                System.out.print("Enter a name for your character: ");
                characterName = scanner.inputCharacterName();
            } catch (Exception e) {
                System.out.print(e.getMessage() + " ");
            }
        }

        player = gameManager.createPlayerCharacter(characterName);
        System.out.printf("Created -> %s%n", player.getInfo());

        // ----- GAME BEGINS
        while (player.checkIfCharacterIsAlive()) {
            System.out.println();
            boolean gameMustClose = false;
            int turnCounter = 0;

            // Init combat
            enemy = gameManager.createNewEnemy();
            System.out.printf("--- COMBAT %s - A %s appears!%n", ++combatCounter, enemy.getName());

            // ----- COMBAT BEGINS (player vs enemy)
            while (player.checkIfCharacterIsAlive() && enemy.checkIfCharacterIsAlive()) {
                System.out.println();
                System.out.printf("- Turn %s %n", ++turnCounter);

                // Setup method to manage a combat (1 player vs 1 enemy -> return the character who's alive)
                // -> So once a combat is over, back into WHILE_LOOP & create another enemy + add kill to counter
                System.out.println("> " + player.getInfo());
                System.out.println("> " + enemy.getInfo());
                System.out.printf("Actions: %s %nChoose an action: ", gameManager.displayActionChoices());

                // ----- PLAYER CHOSES AN ACTION
                while (true) {
                    try {
                        ActionType selectedAction = scanner.inputAction(); // Manage errors for non-int & non-valid values

                        switch (selectedAction) {
                            case ActionType.ATTACK -> player.attackAction(enemy);
                            case ActionType.CAST_SPELL -> player.spellAction(enemy);
                            case ActionType.DRINK_POTION -> player.usePotion();
                            case ActionType.LEAVE_GAME -> gameMustClose = true; // Needs better setup
                        }

                        break;
                    } catch (WrongUserInput | NotEnoughResourceException | MissingItemException | ResourceFullException e) {
                        System.out.println(e.getMessage());
                        System.out.printf("%s %nChose an action: ", gameManager.displayActionChoices());
                    } catch (Exception e) {
                        System.out.print(e.getMessage() + " ");
                    }
                }

                if (gameMustClose) {
                    System.out.println(player.getName() + " turns around and flees from his adversary... (game closing)");
                    break;
                }

                if (enemy.checkIfCharacterIsAlive()) {
                    enemy.attackAction(player);
                } else {
                    System.out.printf("%s is dead!%n", enemy.getName());
                    enemiesKilled++;
                    try {
                        player.addPotion();
                        player.regainMana(5);
                    } catch (Exception e) {
                        System.out.print(e.getMessage() + " ");
                    }
                }
            }

            if (gameMustClose) {
                System.out.println(player.getName() + " managed to flee from the fight! (game closed)");
                break;
            }

            // Check if this is read by the console
            if (!player.checkIfCharacterIsAlive()) {
                System.out.printf("----- Oh no! %s was killed by %s! -----%n", player.getName(), enemy.getName());
                System.out.println("Total enemies killed: " + enemiesKilled);
                break;
            }
        }


        // ----- FILE MANAGER
        // TODO: setup file reader / write methods


        scanner.getScanner().close();
        // Bonus: when all done, rework display in CLI (not user-friendly, right now)
    }
}