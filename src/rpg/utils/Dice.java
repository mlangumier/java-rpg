package rpg.utils;

import java.util.Random;

// TODO: Turn into interface and create dice types when needed ?
// TODO: Use DiceType instead of int ? (-> would need manager to transform value & then calculate)
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

    /**
     * Roll a dice of the chosen value
     * @return The value of the dice roll
     */
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
