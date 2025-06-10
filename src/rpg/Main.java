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
import rpg.services.ScoreManager;

public class Main {
    public static void main(String[] args) {
        ScannerManager scannerManager = new ScannerManager();
        GameManager gameManager = new GameManager();
        ScoreManager scoreManager = new ScoreManager();

        Player player;
        Enemy enemy;
        int combatCounter = 0;
        int enemiesKilled = 0;

        System.out.println("\n========= JAVA RPG ==========\n");

        // INFO -- CHARACTER CREATION
        while (true) {
            try {
                System.out.print("Enter a name for your character: ");
                String characterName = scannerManager.inputCharacterName();

                player = gameManager.createPlayerCharacter(characterName);
                System.out.printf("Created -> %s%n", player.getInfo());
                break;
            } catch (Exception e) {
                System.out.print(e.getMessage() + " ");
            }
        }


        // INFO -- GAME BEGINS
        while (player.checkIfCharacterIsAlive()) {
            System.out.println();
            boolean gameMustClose = false;
            int turnCounter = 0;

            // INFO -- Init combat
            enemy = gameManager.createNewEnemy();
            System.out.printf("===== Combat %s - A %s appears! =====%n", ++combatCounter, enemy.getName());

            // INFO -- COMBAT BEGINS (player vs enemy)
            while (player.checkIfCharacterIsAlive() && enemy.checkIfCharacterIsAlive()) {
                System.out.println();
                System.out.printf("===== Turn %s =====%n", ++turnCounter);

                // Setup method to manage a combat (1 player vs 1 enemy -> return the character who's alive)
                // -> So once a combat is over, back into WHILE_LOOP & create another enemy + add kill to counter
                System.out.println("- " + player.getInfo());
                System.out.println("- " + enemy.getInfo());
                System.out.printf("> Actions: %s %n> Choose an action: ", gameManager.displayActionChoices());

                // INFO -- PLAYER CHOSES AN ACTION
                while (true) {
                    try {
                        ActionType selectedAction = scannerManager.inputAction();

                        switch (selectedAction) {
                            case ActionType.ATTACK -> player.attackAction(enemy);
                            case ActionType.CAST_SPELL -> player.spellAction(enemy);
                            case ActionType.DRINK_POTION -> player.usePotion();
                            case ActionType.LEAVE_GAME -> gameMustClose = true; // Needs better setup
                        }

                        break;
                    } catch (WrongUserInput | NotEnoughResourceException | MissingItemException |
                             ResourceFullException e) {
                        System.out.println(e.getMessage());
                        System.out.printf("%s %n> Chose an action: ", gameManager.displayActionChoices());
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid number.");
                        System.out.printf("%s %n> Chose an action: ", gameManager.displayActionChoices());
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
                    System.out.printf("=== %s is dead!%n", enemy.getName());
                    enemiesKilled++;
                    try {
                        System.out.println("\nLooting the body and resting for a bit.");
                        System.out.println("You found one (1) potion.");
                        player.addPotion();
                        player.regainMana(15);
                    } catch (ResourceFullException ignored) {
                        // Adapt this later if setup inventory space
                    } catch (Exception e) {
                        System.out.print(e.getMessage() + " ");
                    }
                }
            }

            // INFO -- CLOSING GAME
            if (gameMustClose || !player.checkIfCharacterIsAlive()) {
                if (gameMustClose) {
                    System.out.println(player.getName() + " managed to flee from the fight! (game closed)");
                } else {
                    System.out.printf("%n----- Oh no! %s was killed by %s! -----%n", player.getName(), enemy.getName());
                    System.out.printf("> %s killed: %s%n", player.getName(), enemiesKilled);
                }
                break;
            }
        }

        // INFO -- FILE MANAGER
        try {
            System.out.println("\n========== SCORES ==========\n");
            scoreManager.addScore(player.getName(), enemiesKilled);
            scoreManager.writeScores();

            scoreManager.displayScores();
        } catch (Exception e) {
            System.out.printf("%n> Error: %s (%s) %n", e.getMessage(), e.getClass().getName());
        }
        scannerManager.getScanner().close();

        // TODO (Bonus): when all done, rework display in CLI (not user-friendly, right now)
    }
}