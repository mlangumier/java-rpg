package rpg.services;

import rpg.enums.ActionType;
import rpg.exceptions.WrongUserInput;

import java.util.Scanner;

public class ScannerManager {
    private Scanner scanner;

    public ScannerManager() {
        scanner = new Scanner(System.in);
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    // ----- METHODS

    /**
     * Asks the player to enter a name for their character in the console
     *
     * @return The name the player chose for their hero
     * @throws WrongUserInput if the player enters a non-valid input.
     */
    public String inputCharacterName() throws WrongUserInput {
        String characterName = this.scanner.nextLine().trim();
        this.checkCharacterName(characterName);

        return characterName;
    }

    /**
     * Asks the player to enter a number corresponding to an action his hero can take during the turn
     *
     * @return The action the player has selected
     * @throws WrongUserInput If the player selected an action not available in the list
     */
    public ActionType inputAction() throws WrongUserInput {
        int actionNbr = Integer.parseInt(this.scanner.nextLine().trim());
        checkSelectedAction(actionNbr - 1);

        return ActionType.values()[actionNbr - 1];
    }

    // ----- EXCEPTIONS CHECKERS

    /**
     * Checks that the character name the player has entered isn't empty
     *
     * @param characterName Name the user has entered for his character
     * @throws WrongUserInput If the user sent an empty String
     */
    private void checkCharacterName(String characterName) throws WrongUserInput {
        if (characterName.isEmpty()) {
            throw new WrongUserInput("Your character name cannot be empty.");
        }
    }

    /**
     * Checks that the option the player chose is among the available actions
     *
     * @param actionNbr Number that the player entered to use the corresponding action
     * @throws WrongUserInput If the player entered a number not present among the available choices
     */
    private void checkSelectedAction(int actionNbr) throws WrongUserInput {
        if (actionNbr < 0 || actionNbr > ActionType.values().length - 1) {
            throw new WrongUserInput("Select an action from the list.");
        }
    }

    @Override
    public String toString() {
        return "ScannerManager{" +
                "scanner=" + scanner +
                '}';
    }

}
