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
     *
     * @return
     * @throws WrongUserInput
     */
    public String inputCharacterName() throws WrongUserInput {
        String characterName = this.scanner.nextLine().trim();
        this.checkCharacterName(characterName);

        return characterName;
    }

    /**
     *
     * @return
     * @throws WrongUserInput
     */
    public ActionType inputAction() throws WrongUserInput {
        int actionNbr = this.scanner.nextInt();
        checkSelectedAction(actionNbr - 1);

        return ActionType.values()[actionNbr - 1];
    }

    // ----- EXCEPTIONS CHECKERS

    /**
     *
     * @param characterName
     * @throws WrongUserInput
     */
    private void checkCharacterName(String characterName) throws WrongUserInput {
        if (characterName.isEmpty()) {
            throw new WrongUserInput("Your character name cannot be empty.");
        }
    }

    /**
     *
     * @param actionNbr
     * @throws WrongUserInput
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
