package rpg.utils;

import rpg.enums.ActionType;

public class Action {
    public ActionType getActionType(int value) {
        return switch (value) {
            case 1 -> ActionType.ATTACK;
            case 2 -> ActionType.CAST_SPELL;
            case 3 -> ActionType.DRINK_POTION;
            case 9 -> ActionType.LEAVE_GAME;
            default -> ActionType.UNKNOWN;
        };
    }

    public String displayActionChoices () {
        return "1. Attack | 2. Cast Spell | 3. Drink Potion | 9. Exit Game";
    }
}
