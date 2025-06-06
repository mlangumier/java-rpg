package rpg.enums;

public enum DiceType {
    FOUR(4),
    SIX(6),
    EIGHT(8),
    TWELVE(12),
    TWENTY(20);

    public final int value;

    DiceType (int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
