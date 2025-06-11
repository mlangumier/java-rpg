package rpg;

import rpg.character.enemy.Enemy;
import rpg.character.hero.Hero;
import rpg.enums.ActionType;
import rpg.exceptions.*;
import rpg.services.GameManager;
import rpg.services.ScannerManager;
import rpg.services.ScoreManager;

public class Main {
    public static void main(String[] args) {
        ScannerManager scannerManager = new ScannerManager();
        GameManager gameManager = new GameManager();
        ScoreManager scoreManager = new ScoreManager();

        Hero hero;
        Enemy enemy;
        int combatCounter = 0;
        int enemiesKilled = 0;

        System.out.println("\n========= JAVA RPG ==========\n");

        // INFO -- CHARACTER CREATION
        while (true) {
            try {
                System.out.print("Enter a name for your character: ");
                String characterName = scannerManager.inputCharacterName();

                hero = gameManager.createHero(characterName);
                System.out.printf("Created -> %s%n", hero.getInfo());
                break;
            } catch (Exception e) {
                System.out.print(e.getMessage() + " ");
            }
        }


        // INFO -- GAME BEGINS
        while (hero.checkIsAlive()) {
            System.out.println();
            boolean gameMustClose = false;
            int turnCounter = 0;

            // INFO -- Init combat
            enemy = gameManager.createNewEnemy();
            hero.setIsPotionAlreadyUsed(false);
            System.out.printf("===== Combat %s - A %s appears! =====%n", ++combatCounter, enemy.getName());

            // INFO -- COMBAT BEGINS (hero vs enemy)
            while (hero.checkIsAlive() && enemy.checkIsAlive()) {
                System.out.println();
                System.out.printf("===== Turn %s =====%n", ++turnCounter);

                // Setup method to manage a combat (1 hero vs 1 enemy -> return the character who's alive)
                // -> So once a combat is over, back into WHILE_LOOP & create another enemy + add kill to counter
                System.out.println("- " + hero.getInfo());
                System.out.println("- " + enemy.getInfo());
                System.out.printf("> Actions: %s %n> Choose an action: ", gameManager.displayActionChoices());

                // INFO -- PLAYER CHOSES AN ACTION
                while (true) {
                    try {
                        ActionType selectedAction = scannerManager.inputAction();

                        switch (selectedAction) {
                            case ActionType.ATTACK -> hero.attackAction(enemy);
                            case ActionType.CAST_SPELL -> hero.spellAction(enemy);
                            case ActionType.DRINK_POTION -> hero.usePotion();
                            case ActionType.LEAVE_GAME -> gameMustClose = true; // Needs better setup
                        }

                        break;
                    } catch (WrongUserInput | NotEnoughResourceException | MissingItemException | ItemNotAvailable |
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
                    System.out.println(hero.getName() + " turns around and flees from his adversary... (game closing)");
                    break;
                }

                if (enemy.checkIsAlive()) {
                    enemy.attackAction(hero);
                } else {
                    System.out.printf("=== %s is dead!%n", enemy.getName());
                    enemiesKilled++;
                    try {
                        System.out.println("\nLooting the body and resting for a bit.");
//                        System.out.println("You found one (1) potion.");
//                        hero.addPotion();
                        hero.regainMana(15);
                    } catch (ResourceFullException ignored) {
                        // Adapt this later if setup inventory space
                    } catch (Exception e) {
                        System.out.print(e.getMessage() + " ");
                    }
                }
            }

            // INFO -- CLOSING GAME
            if (gameMustClose || !hero.checkIsAlive()) {
                if (gameMustClose) {
                    System.out.println(hero.getName() + " managed to flee from the fight! (game closed)");
                } else {
                    System.out.printf("%n----- Oh no! %s was killed by %s! -----%n", hero.getName(), enemy.getName());
                    System.out.printf("> %s killed: %s%n", hero.getName(), enemiesKilled);
                }
                break;
            }
        }

        // INFO -- FILE MANAGER
        try {
            System.out.println("\n========== SCORES ==========\n");
            scoreManager.addScore(hero.getName(), enemiesKilled);
            scoreManager.writeScores();

            scoreManager.displayScores();
        } catch (Exception e) {
            System.out.printf("%n> Error: %s (%s) %n", e.getMessage(), e.getClass().getName());
        }
        scannerManager.getScanner().close();

        // TODO (Bonus): when all done, rework display in CLI (not user-friendly, right now)
    }
}