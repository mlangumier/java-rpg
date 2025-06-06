package rpg.utils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

// TODO: Turn into abstract parent and create dice types when needed ?
// TODO: Use DiceType instead of int ? (-> would need manager to transform value & then calculate)
public class Dice {
    private int value;
    private final Random random = new Random();

    public Dice (int value) {
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

    public int rollMultipleDice(int nbrDice) {
        String[] rolls = new String[nbrDice];
        int total = 0;

        for (int i = 0; i < nbrDice; i++) {
            int roll = this.rollDice();
            total += roll;
            rolls[i] = String.valueOf(roll);
        }

        System.out.print(Arrays.toString(rolls));
        return total;
    }

    @Override
    public String toString() {
        return "Dice{" +
                "value=" + value +
                '}';
    }
}
