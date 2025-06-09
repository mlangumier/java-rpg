package rpg.enums;

public enum ActionType {
    UNKNOWN(0),
    ATTACK(1),
    CAST_SPELL(2),
    DRINK_POTION(3),
    LEAVE_GAME(9);

    public final int value;

    ActionType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
