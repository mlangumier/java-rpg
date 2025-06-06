package rpg.utils;

import java.util.Random;

public class Dice {
    private int value;
    Random random = new Random();

    public Dice(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int rollDice() {
        return random.nextInt(value) + 1;
    }

    @Override
    public String toString() {
        return "Dice{" +
                "value=" + value +
                '}';
    }
}
